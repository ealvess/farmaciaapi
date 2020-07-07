package com.farmacia.farmaciaapi.resource;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.repository.ItemSaidaCorrelatoRepository;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaCorrelatoFilter;
import com.farmacia.farmaciaapi.service.CorrelatoService;

@RestController
@RequestMapping("/saidacorrelato")
public class ItemSaidaCorrelatoResource {

	@Autowired
	private ItemSaidaCorrelatoRepository itemSaidaCorrelatoRespository;

	@Autowired
	private CorrelatoService correlatoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	private BigDecimal valorTotal;

	@GetMapping
	public Page<ItemSaidaCorrelato> pesquisar(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable) {
		return itemSaidaCorrelatoRespository.filtrar(itemSaidaCorrelatoFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<ItemSaidaCorrelato> criar(@Validated @RequestBody ItemSaidaCorrelato itemSaidaCorrelato,
			HttpServletResponse response) {
		ItemSaidaCorrelato itemSaida = itemSaidaCorrelatoRespository.save(itemSaidaCorrelato);

		valorTotal = calcularValorTotal(itemSaida.getQuantidade(), itemSaida.getValorUnitario());
		atualizarQuantidade(itemSaida.getCorrelato().getCodigo(), itemSaida.getQuantidade());

		itemSaida.setTotal(valorTotal);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, itemSaida.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(itemSaida);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<ItemSaidaCorrelato> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.itemSaidaCorrelatoRespository.findById(codigo).map(itemSaida -> ResponseEntity.ok(itemSaida))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{codigo}/quantidade")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarQuantidade(@PathVariable Long codigo, Integer quantidade) {
		correlatoService.atualizarQuantidade(codigo, quantidade);
	}

	public BigDecimal calcularValorTotal(Integer quantidade, BigDecimal valorUnitario) {
		int qtd = quantidade.intValue();
		BigDecimal temp = new BigDecimal(qtd);

		BigDecimal total = valorUnitario.multiply(temp);
		return total;
	}

}
