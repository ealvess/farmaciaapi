package com.farmacia.farmaciaapi.resource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaCorrelatoPorMes;
import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.exceptionhandler.FarmaciaExceptionHandler.Erro;
import com.farmacia.farmaciaapi.model.EntradaCorrelato;
import com.farmacia.farmaciaapi.repository.EntradaCorrelatoRepository;
import com.farmacia.farmaciaapi.repository.filter.EntradaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaCorrelatos;
import com.farmacia.farmaciaapi.service.EntradaCorrelatoService;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@RestController
@RequestMapping("/entradacorrelatos")
public class EntradaCorrelatoResource {

	@Autowired
	private EntradaCorrelatoRepository entradaCorrelatoRepositrory;

	@Autowired
	private EntradaCorrelatoService entradaCorrelatoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/relatorios/por-mes")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO')")
	public ResponseEntity<byte[]> relatorioPorMes(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim) throws Exception {
		byte[] relatorio = entradaCorrelatoService.relatorioPorMes(inicio, fim); 
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@GetMapping("/estatisticas/por-mes")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_CORRELATO')")
	public List<EstatisticaEntradaCorrelatoPorMes> porMedicamento() {
		return entradaCorrelatoRepositrory.porCorrelato(LocalDate.now());
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_CORRELATO')")
	public Page<EntradaCorrelato> pesquisar(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable) {
		return entradaCorrelatoRepositrory.filtrar(entradaCorrelatoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_CORRELATO')")
	public Page<ResumoEntradaCorrelatos> resumo(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable) {
		return entradaCorrelatoRepositrory.resumo(entradaCorrelatoFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_CORRELATO')")
	public List<EntradaCorrelato> listarTodas(){
		return entradaCorrelatoRepositrory.findAll();
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_CORRELATO')")
	public ResponseEntity<EntradaCorrelato> buscarPeloCodigo(@PathVariable Long codigo) {
		return entradaCorrelatoRepositrory.findById(codigo).map(correlato -> ResponseEntity.ok(correlato))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ENTRADA_DE_CORRELATO')")
	public ResponseEntity<EntradaCorrelato> criar(@Validated @RequestBody EntradaCorrelato entradaCorrelato, HttpServletResponse response) {
		EntradaCorrelato correlatoSalvo = entradaCorrelatoService.salvar(entradaCorrelato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, correlatoSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(correlatoSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_ENTRADA_DE_CORRELATO')")
	public void remover(@PathVariable Long codigo) {
		entradaCorrelatoRepositrory.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ENTRADA_DE_CORRELATO')")
	public ResponseEntity<EntradaCorrelato> atualizar(@PathVariable Long codigo, @Validated @RequestBody EntradaCorrelato entradaCorrelato) {
		EntradaCorrelato correlatoSalvo = entradaCorrelatoService.atualizar(codigo, entradaCorrelato);
		return ResponseEntity.ok(correlatoSalvo);
	}
	

	@ExceptionHandler({ObjetoInexistenteOuInativoException.class})
	public ResponseEntity<Object> handleCategoriaInexistenteOuInativaException(ObjetoInexistenteOuInativoException ex){
		String mensagemUsuario = messageSource.getMessage("correlatooufornecedor.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
