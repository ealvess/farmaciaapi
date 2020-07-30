package com.farmacia.farmaciaapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.exceptionhandler.FarmaciaExceptionHandler.Erro;
import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.repository.CorrelatoRepository;
import com.farmacia.farmaciaapi.repository.filter.CorrelatoFilter;
import com.farmacia.farmaciaapi.service.CorrelatoService;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@RestController
@RequestMapping("/correlatos")
public class CorrelatoResource {

	@Autowired
	private CorrelatoRepository correlatoRepository;
	
	@Autowired
	private CorrelatoService correlatoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CORRELATO')")
	public Page<Correlato> filtrar(CorrelatoFilter correlatoFilter, Pageable pageable) {
		return correlatoRepository.filtrar(correlatoFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CORRELATO')")
	public List<Correlato> listarTodos(){
		return correlatoRepository.findAll();
	}
 
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CORRELATO')")
	public ResponseEntity<Correlato> criar(@Validated @RequestBody Correlato correlato, HttpServletResponse response) {
		Correlato correlatoSalvo = correlatoService.salvar(correlato);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, correlatoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(correlatoSalvo);
	}
	

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CORRELATO')")
	public ResponseEntity<Correlato> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.correlatoRepository.findById(codigo).map(correlato-> ResponseEntity.ok(correlato))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CORRELATO')")
	public void remover(@PathVariable Long codigo) {
		correlatoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CORRELATO')")
	public ResponseEntity<Correlato> atualizar(@PathVariable Long codigo, @Validated @RequestBody Correlato correlato) {
		Correlato correlatoSalvo = correlatoService.atualizar(codigo, correlato);
		return ResponseEntity.ok(correlatoSalvo);
	}
	

	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CORRELATO')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		correlatoService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
	@ExceptionHandler({ObjetoInexistenteOuInativoException.class})
	public ResponseEntity<Object> handleCategoriaInexistenteOuInativaException(ObjetoInexistenteOuInativoException ex){
		String mensagemUsuario = messageSource.getMessage("correlato.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	
}

