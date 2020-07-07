package com.farmacia.farmaciaapi.resource;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.farmacia.farmaciaapi.model.GrupoCorrelato;
import com.farmacia.farmaciaapi.repository.GrupoCorrelatoRepository;
import com.farmacia.farmaciaapi.service.GrupoCorrelatoService;

@RestController
@RequestMapping("/grupo")
public class GrupoCorrelatoResource {

	@Autowired
	private GrupoCorrelatoRepository grupoCorrelatoRepository;
	
	@Autowired
	private GrupoCorrelatoService grupoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<GrupoCorrelato> listar() {
		return grupoCorrelatoRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<GrupoCorrelato> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.grupoCorrelatoRepository.findById(codigo).map(grupoCorrelato -> ResponseEntity.ok(grupoCorrelato))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<GrupoCorrelato> criar(@Validated @RequestBody GrupoCorrelato grupo,
			HttpServletResponse response) {
		GrupoCorrelato grupoSalvo = grupoCorrelatoRepository.save(grupo);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		grupoCorrelatoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<GrupoCorrelato> atualizar(@PathVariable Long codigo,
			@Validated @RequestBody GrupoCorrelato grupo) {
		GrupoCorrelato grupoSalvo = grupoService.atualizar(codigo, grupo);
		return ResponseEntity.ok(grupoSalvo);
	}
}
