package com.farmacia.farmaciaapi.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

import com.farmacia.farmaciaapi.dto.EstatisticaSaidaMedicamentoPorPaciente;
import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento;
import com.farmacia.farmaciaapi.repository.ItemSaidaMedicamentoRepository;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeMedicamentos;
import com.farmacia.farmaciaapi.service.EntradaMedicamentoService;
import com.farmacia.farmaciaapi.service.SaidaDeMedicamentoService;

@RestController
@RequestMapping("/saidamedicamentos")
public class ItemSaidaMedicamentoResource {

	@Autowired
	private ItemSaidaMedicamentoRepository itemSaidaMedicamentoRepository;

	@Autowired
	private EntradaMedicamentoService entradaMedicamentoService;
	
	@Autowired
	private SaidaDeMedicamentoService saidaDeMedicamentoPorPacienteService;

	@Autowired
	private ApplicationEventPublisher publisher;

	private BigDecimal valorTotal;
	
	@GetMapping("/estatisticas/por-mes")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public List<EstatisticaSaidaMedicamentoPorPaciente> porMes() {
		return itemSaidaMedicamentoRepository.porMes(LocalDate.now());
	}
	
	/*@GetMapping("/relatorios/por-paciente")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public ResponseEntity<byte[]> relatorioPorPpaciente(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim) throws Exception {
		byte[] relatorio = saidaDeMedicamentoPorPacienteService.relatorioPorPessoa(inicio, fim);
		
		return ResponseEntity.ok()
				.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}*/

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public Page<ItemSaidaMedicamento> pesquisar(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter,
			Pageable pageable) {
		return itemSaidaMedicamentoRepository.filtrar(itemSaidaMedicamentoFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public Page<ResumoSaidaDeMedicamentos> resumo(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, 
			Pageable pageable) {
		return itemSaidaMedicamentoRepository.resumo(itemSaidaMedicamentoFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ITEM_SAIDA_MEDICAMENTO')")
	public ResponseEntity<ItemSaidaMedicamento> criar(@Validated @RequestBody ItemSaidaMedicamento itemSaidaMedicamento,
			HttpServletResponse response) {
		ItemSaidaMedicamento itemSaida = itemSaidaMedicamentoRepository.save(itemSaidaMedicamento);

		valorTotal = calcularValorTotal(itemSaida.getQuantidade(), itemSaida.getValorUnitario());
		atualizarQuantidade(itemSaida.getEntradaMedicamento().getCodigo(), itemSaida.getQuantidade());

		itemSaida.setTotal(valorTotal);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, itemSaida.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(itemSaida);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO')")
	public ResponseEntity<ItemSaidaMedicamento> buscarPeloCodigo(@PathVariable Long codigo) {
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
