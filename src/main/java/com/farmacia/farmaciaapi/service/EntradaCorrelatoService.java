package com.farmacia.farmaciaapi.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaCorrelatoPorMes;
import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.model.EntradaCorrelato;
import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.repository.CorrelatoRepository;
import com.farmacia.farmaciaapi.repository.EntradaCorrelatoRepository;
import com.farmacia.farmaciaapi.repository.FornecedorRepository;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EntradaCorrelatoService {

	@Autowired
	private EntradaCorrelatoRepository entradaCorrelatoRepository;
	
	@Autowired
	private CorrelatoRepository correlatoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	private BigDecimal qtdNova;
	
	public byte[] relatorioPorMes(LocalDate inicio, LocalDate fim) throws Exception {
		List<EstatisticaEntradaCorrelatoPorMes> dados = entradaCorrelatoRepository.porMes(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/entrada_correlatos_por_mes.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public EntradaCorrelato atualizar(Long codigo, EntradaCorrelato entradaCorrelato) {

		EntradaCorrelato correlatoSalvo = buscarCorrelatoPeloCodigo(codigo);

		BeanUtils.copyProperties(entradaCorrelato, correlatoSalvo, "codigo");

		return this.entradaCorrelatoRepository.save(correlatoSalvo);
	}

	public void atualizarQuantidade(Long codigo, BigDecimal quantidade) {
		EntradaCorrelato correlatoSalvo = buscarCorrelatoPeloCodigo(codigo);
		qtdNova = correlatoSalvo.getQuantidade().subtract(quantidade);
		correlatoSalvo.setQuantidade(qtdNova);
		entradaCorrelatoRepository.save(correlatoSalvo);
	}
	
	public EntradaCorrelato salvar(EntradaCorrelato entradaCorrelato) {
		Optional<Correlato> correlatoOpt = correlatoRepository.findById(entradaCorrelato.getCorrelato().getCodigo());
		Optional<Fornecedor> forencedorOpt = fornecedorRepository.findById(entradaCorrelato.getFornecedor().getCodigo());

	    if (!correlatoOpt.isPresent() || correlatoOpt.get().isInativo() || !forencedorOpt.isPresent() || forencedorOpt.get().isInativo()) {
	        throw new ObjetoInexistenteOuInativoException();
	    }
	    
	    return entradaCorrelatoRepository.save(entradaCorrelato);
	}

	public EntradaCorrelato buscarCorrelatoPeloCodigo(Long codigo) {
		EntradaCorrelato correlatoSalvo = this.entradaCorrelatoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return correlatoSalvo;
	}

}
