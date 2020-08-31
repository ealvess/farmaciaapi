package com.farmacia.farmaciaapi.resource;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamentoPorCentroDeCusto;
import com.farmacia.farmaciaapi.repository.ItemSaidaMedicamentoPorCentroDeCustoRepository;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoPorCentroDeCustoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeMedicamentosPorCentroDeCusto;
import com.farmacia.farmaciaapi.service.EntradaMedicamentoService;

@RestController
@RequestMapping("/saidamedicamentoscentrodecusto")
public class ItemSaidaMedicamentoPorCentroDeCustoResource {

	@Autowired
	private ItemSaidaMedicamentoPorCentroDeCustoRepository itemSaidaMedicamentoRepository;

	@Autowired
	private EntradaMedicamentoService entradaMedicamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	private BigDecimal valorTotal;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public Page<ItemSaidaMedicamentoPorCentroDeCusto> pesquisar(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter,
			Pageable pageable) {
		return itemSaidaMedicamentoRepository.filtrar(itemSaidaMedicamentoFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public Page<ResumoSaidaDeMedicamentosPorCentroDeCusto> resumo(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter, 
			Pageable pageable) {
		return itemSaidaMedicamentoRepository.resumo(itemSaidaMedicamentoFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ITEM_SAIDA_MEDICAMENTO')")
	public ResponseEntity<ItemSaidaMedicamentoPorCentroDeCusto> criar(@Validated @RequestBody ItemSaidaMedicamentoPorCentroDeCusto itemSaidaMedicamento,
			HttpServletResponse response) {
		ItemSaidaMedicamentoPorCentroDeCusto itemSaida = itemSaidaMedicamentoRepository.save(itemSaidaMedicamento);

		valorTotal = calcularValorTotal(itemSaida.getQuantidade(), itemSaida.getValorUnitario());
		atualizarQuantidade(itemSaida.getEntradaMedicamento().getCodigo(), itemSaida.getQuantidade());

		itemSaida.setTotal(valorTotal);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, itemSaida.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(itemSaida);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public ResponseEntity<ItemSaidaMedicamentoPorCentroDeCusto> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.itemSaidaMedicamentoRepository.findById(codigo).map(itemSaida -> ResponseEntity.ok(itemSaida))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{codigo}/quantidade")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarQuantidade(@PathVariable Long codigo, BigDecimal quantidade) {
		entradaMedicamentoService.atualizarQuantidade(codigo, quantidade);
	}

	public BigDecimal calcularValorTotal(BigDecimal quantidade, BigDecimal valorUnitario) {
		int qtd = quantidade.intValue();
		BigDecimal temp = new BigDecimal(qtd);

		BigDecimal total = valorUnitario.multiply(temp);
		return total;
	}

}
