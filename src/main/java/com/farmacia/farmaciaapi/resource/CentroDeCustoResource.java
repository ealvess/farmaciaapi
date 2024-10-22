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
import com.farmacia.farmaciaapi.model.CentroDeCusto;
import com.farmacia.farmaciaapi.repository.CentroDeCustoRepository;
import com.farmacia.farmaciaapi.repository.filter.CentroDeCustoFilter;
import com.farmacia.farmaciaapi.service.CentroDeCustoService;

@RestController
@RequestMapping("/centrodecusto")
public class CentroDeCustoResource {

	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;

	@Autowired
	private CentroDeCustoService centroDeCustoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CENTRO_DE_CUSTO')")
	public Page<CentroDeCusto> filtrar(CentroDeCustoFilter centroDeCustoFilter, Pageable pageable) {
		return centroDeCustoRepository.filtrar(centroDeCustoFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CENTRO_DE_CUSTO')")
	public List<CentroDeCusto> listarTodas(){
		return centroDeCustoRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CENTRO_DE_CUSTO')")
	public ResponseEntity<CentroDeCusto> criar(@Validated @RequestBody CentroDeCusto centroDeCusto,
			HttpServletResponse response) {
		CentroDeCusto centroDeCustoSalvo = centroDeCustoRepository.save(centroDeCusto);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, centroDeCustoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(centroDeCustoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<CentroDeCusto> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.centroDeCustoRepository.findById(codigo).map(centroDeCusto -> ResponseEntity.ok(centroDeCusto))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		centroDeCustoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<CentroDeCusto> atualizar(@PathVariable Long codigo,
			@Validated @RequestBody CentroDeCusto centroDeCusto) {
		CentroDeCusto centroDeCustoSalvo = centroDeCustoService.atualizar(codigo, centroDeCusto);
		return ResponseEntity.ok(centroDeCustoSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CENTRO_DE_CUSTO')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		centroDeCustoService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
