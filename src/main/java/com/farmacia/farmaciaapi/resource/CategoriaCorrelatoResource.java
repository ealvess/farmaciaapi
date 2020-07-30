package com.farmacia.farmaciaapi.resource;

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
import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.repository.CategoriaCorrelatoRepository;
import com.farmacia.farmaciaapi.repository.filter.CategoriaCorrelatoFilter;
import com.farmacia.farmaciaapi.service.CategoriaCorrelatoService;

@RestController
@RequestMapping("/categoriascorrelato")
public class CategoriaCorrelatoResource {

	@Autowired
	private CategoriaCorrelatoRepository categoriaRepository;

	@Autowired
	private CategoriaCorrelatoService categoriaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA_DE_CORRELATO')")
	public Page<CategoriaCorrelato> filtrar(CategoriaCorrelatoFilter categoriaFilter, Pageable pageable) {
		return categoriaRepository.filtrar(categoriaFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA_DE_CORRELATO')")
	public List<CategoriaCorrelato> listarTodas(){
		return categoriaRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA_DE_CORRELATO')")
	public ResponseEntity<CategoriaCorrelato> criar(@Validated @RequestBody CategoriaCorrelato categoria, HttpServletResponse response) {
		CategoriaCorrelato categoriaSalva = categoriaRepository.save(categoria);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA_DE_CORRELATO')")
	public ResponseEntity<CategoriaCorrelato> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo).map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CATEGORIA_DE_CORRELATO')")
	public void remover(@PathVariable Long codigo) {
		categoriaRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA_DE_CORRELATO')")
	public ResponseEntity<CategoriaCorrelato> atualizar(@PathVariable Long codigo, @Validated @RequestBody CategoriaCorrelato categoria) {
		CategoriaCorrelato categoriaSalva = categoriaService.atualizar(codigo, categoria);
		return ResponseEntity.ok(categoriaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA_DE_CORRELATO')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		categoriaService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
