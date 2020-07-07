package com.farmacia.farmaciaapi.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.MedicamentoRepository;
import com.farmacia.farmaciaapi.repository.filter.MedicamentoFilter;
import com.farmacia.farmaciaapi.service.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoResource {

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Medicamento> pesquisar(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		return medicamentoRepository.filtrar(medicamentoFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<Medicamento> criar(@Validated @RequestBody Medicamento medicamento,
			HttpServletResponse response) {
		Medicamento medicamentoSalvo = medicamentoRepository.save(medicamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicamentoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Medicamento> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.medicamentoRepository.findById(codigo).map(medicamento -> ResponseEntity.ok(medicamento))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		medicamentoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Medicamento> atualizar(@PathVariable Long codigo,
			@Validated @RequestBody Medicamento medicamento) {
		Medicamento medicamentoSalvo = medicamentoService.atualizar(codigo, medicamento);
		return ResponseEntity.ok(medicamentoSalvo);
	}

}
