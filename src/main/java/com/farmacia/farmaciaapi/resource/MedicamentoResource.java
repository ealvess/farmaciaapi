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
import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.MedicamentoRepository;
import com.farmacia.farmaciaapi.repository.filter.MedicamentoFilter;
import com.farmacia.farmaciaapi.service.MedicamentoService;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoResource {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO')")
	public Page<Medicamento> filtrar(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		return medicamentoRepository.filtrar(medicamentoFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO')")
	public List<Medicamento> listarTodas(){
		return medicamentoRepository.findAll();
	}
 
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICAMENTO')")
	public ResponseEntity<Medicamento> criar(@Validated @RequestBody Medicamento medicamento, HttpServletResponse response) {
		Medicamento medicamentoSalvo = medicamentoService.salvar(medicamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicamentoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
	}
	

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO')")
	public ResponseEntity<Medicamento> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.medicamentoRepository.findById(codigo).map(medicamento -> ResponseEntity.ok(medicamento))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MEDICAMENTO')")
	public void remover(@PathVariable Long codigo) {
		medicamentoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICAMENTO')")
	public ResponseEntity<Medicamento> atualizar(@PathVariable Long codigo, @Validated @RequestBody Medicamento medicamento) {
		Medicamento medicamentoSalvo = medicamentoService.atualizar(codigo, medicamento);
		return ResponseEntity.ok(medicamentoSalvo);
	}
	

	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICAMENTO')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		medicamentoService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
	@ExceptionHandler({ObjetoInexistenteOuInativoException.class})
	public ResponseEntity<Object> handleCategoriaInexistenteOuInativaException(ObjetoInexistenteOuInativoException ex){
		String mensagemUsuario = messageSource.getMessage("categoria.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	
}

