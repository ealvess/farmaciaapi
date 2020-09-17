package com.farmacia.farmaciaapi.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamentoPorMes;
import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.EntradaMedicamentoRepository;
import com.farmacia.farmaciaapi.repository.FornecedorRepository;
import com.farmacia.farmaciaapi.repository.MedicamentoRepository;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EntradaMedicamentoService {

	@Autowired
	private EntradaMedicamentoRepository entradaMedicamentoRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	private BigDecimal qtdNova;
	
	public byte[] relatorioPorMes(LocalDate inicio, LocalDate fim) throws Exception {
		List<EstatisticaEntradaMedicamentoPorMes> dados = entradaMedicamentoRepository.porMes(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/entrada_medicamentos_por_mes.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public EntradaMedicamento atualizar(Long codigo, EntradaMedicamento entradaMedicamento) {

		EntradaMedicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(codigo);

		BeanUtils.copyProperties(entradaMedicamento, medicamentoSalvo, "codigo");

		return this.entradaMedicamentoRepository.save(medicamentoSalvo);
	}

	public void atualizarQuantidade(Long codigo, BigDecimal quantidade) {
		EntradaMedicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(codigo);
		qtdNova = medicamentoSalvo.getQuantidade().subtract(quantidade);
		medicamentoSalvo.setQuantidade(qtdNova);
		entradaMedicamentoRepository.save(medicamentoSalvo);
	}
	

	public EntradaMedicamento salvar(EntradaMedicamento entradaMedicamento) {
		Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(entradaMedicamento.getMedicamento().getCodigo());
		Optional<Fornecedor> forencedorOpt = fornecedorRepository.findById(entradaMedicamento.getFornecedor().getCodigo());

	    if (!medicamentoOpt.isPresent() || medicamentoOpt.get().isInativo() || !forencedorOpt.isPresent() || forencedorOpt.get().isInativo()) {
	        throw new ObjetoInexistenteOuInativoException();
	    }
	    
	    return entradaMedicamentoRepository.save(entradaMedicamento);
	}

	private EntradaMedicamento buscarMedicamentoPeloCodigo(Long codigo) {
		EntradaMedicamento medicamentoSalvo = this.entradaMedicamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return medicamentoSalvo;
	}


}
