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
import com.farmacia.farmaciaapi.model.Paciente;
import com.farmacia.farmaciaapi.repository.PacienteRepository;
import com.farmacia.farmaciaapi.repository.filter.PacienteFilter;
import com.farmacia.farmaciaapi.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Paciente> filtrar(PacienteFilter pacienteFilter, Pageable pageable) {
		return pacienteRepository.filtrar(pacienteFilter, pageable);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Paciente> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.pacienteRepository.findById(codigo).map(paciente -> ResponseEntity.ok(paciente))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Paciente> criar(@Validated @RequestBody Paciente paciente, HttpServletResponse response) {
		Paciente pacienteSalvo = pacienteRepository.save(paciente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pacienteSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pacienteRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long codigo, @Validated @RequestBody Paciente paciente) {
		Paciente pacienteSalvo = pacienteService.atualizar(codigo, paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}
}
