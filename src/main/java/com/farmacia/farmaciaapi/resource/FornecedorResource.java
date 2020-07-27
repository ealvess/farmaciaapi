package com.farmacia.farmaciaapi.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.repository.FornecedorRepository;
import com.farmacia.farmaciaapi.repository.filter.FornecedorFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoFornecedores;
import com.farmacia.farmaciaapi.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FORNECEDOR')")
	public Page<Fornecedor> pesquisar(FornecedorFilter fornecedorFilter, Pageable pageable) {
		return fornecedorRepository.filtrar(fornecedorFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO')")
	public Page<ResumoFornecedores> resumo(FornecedorFilter fornecedorFilter, Pageable pageable) {
		return fornecedorRepository.resumo(fornecedorFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FORNECEDOR')")
	public ResponseEntity<Fornecedor> criar(@Validated @RequestBody Fornecedor fornecedor,
			HttpServletResponse response) {
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, fornecedorSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FORNECEDOR')")
	public ResponseEntity<Fornecedor> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.fornecedorRepository.findById(codigo).map(fornecedor -> ResponseEntity.ok(fornecedor))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FORNECEDOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		fornecedorRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FORNECEDOR')")
	public ResponseEntity<Fornecedor> atualizar(@PathVariable Long codigo,
			@Validated @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorService.atualizar(codigo, fornecedor);
		return ResponseEntity.ok(fornecedorSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FORNECEDOR')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		fornecedorService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
