package com.farmacia.farmaciaapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.repository.MedicoRepository;
import com.farmacia.farmaciaapi.repository.filter.MedicoFilter;
import com.farmacia.farmaciaapi.service.MedicoService;


@RestController
@RequestMapping("/medicos")
public class MedicoResource {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private MedicoService medicoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Medico> pesquisar(MedicoFilter medicoFilter) {
		return medicoRepository.filtrar(medicoFilter);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Medico> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.medicoRepository.findById(codigo).map(medico -> ResponseEntity.ok(medico))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Medico> criar(@Validated @RequestBody Medico medico, HttpServletResponse response) {
		Medico medicoSalvo = medicoRepository.save(medico);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(medicoSalvo);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Medico> atualizar(@PathVariable Long codigo,
			@Validated @RequestBody Medico medico) {
		Medico medicoSalvo = medicoService.atualizar(codigo, medico);
		return ResponseEntity.ok(medicoSalvo);
	}


}
