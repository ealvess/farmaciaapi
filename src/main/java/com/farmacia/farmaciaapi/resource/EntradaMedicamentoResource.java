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

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamento;
import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamentoDia;
import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.exceptionhandler.FarmaciaExceptionHandler.Erro;
import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.repository.EntradaMedicamentoRepository;
import com.farmacia.farmaciaapi.repository.filter.EntradaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaMedicamento;
import com.farmacia.farmaciaapi.service.EntradaMedicamentoService;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@RestController
@RequestMapping("/entradamedicamentos")
public class EntradaMedicamentoResource {

	@Autowired
	private EntradaMedicamentoRepository entradaMedicamentoRepository;

	@Autowired
	private EntradaMedicamentoService entradaMedicamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/estatisticas/por-medicamento")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO')")
	public List<EstatisticaEntradaMedicamento> porMedicamento() {
		return entradaMedicamentoRepository.porMedicamento(LocalDate.now());
	}
	
	@GetMapping("/estatisticas/por-dia")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO')")
	public List<EstatisticaEntradaMedicamentoDia> porDia() {
		return entradaMedicamentoRepository.porDia(LocalDate.now());
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO')")
	public Page<EntradaMedicamento> pesquisar(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable) {
		return entradaMedicamentoRepository.filtrar(entradaMedicamentoFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO')")
	public Page<ResumoEntradaMedicamento> resumo(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable) {
		return entradaMedicamentoRepository.resumo(entradaMedicamentoFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR__ENTRADA_DE_MEDICAMENTO')")
	public List<EntradaMedicamento> listarTodas(){
		return entradaMedicamentoRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ENTRADA_DE_MEDICAMENTO')")
	public ResponseEntity<EntradaMedicamento> criar(@Validated @RequestBody EntradaMedicamento entradaMedicamento,
			HttpServletResponse response) {
		EntradaMedicamento medicamentoSalvo = entradaMedicamentoService.salvar(entradaMedicamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicamentoSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO')")
	public ResponseEntity<EntradaMedicamento> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.entradaMedicamentoRepository.findById(codigo).map(medicamento -> ResponseEntity.ok(medicamento))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_ENTRADA_DE_MEDICAMENTO')")
	public void remover(@PathVariable Long codigo) {
		entradaMedicamentoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ENTRADA_DE_MEDICAMENTO')")
	public ResponseEntity<EntradaMedicamento> atualizar(@PathVariable Long codigo,
			@Validated @RequestBody EntradaMedicamento entradaMedicamento) {
		EntradaMedicamento medicamentoSalvo = entradaMedicamentoService.atualizar(codigo, entradaMedicamento);
		return ResponseEntity.ok(medicamentoSalvo);
	}
	
	@ExceptionHandler({ObjetoInexistenteOuInativoException.class})
	public ResponseEntity<Object> handleCategoriaInexistenteOuInativaException(ObjetoInexistenteOuInativoException ex){
		String mensagemUsuario = messageSource.getMessage("medicamentooufornecedor.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
