package com.farmacia.farmaciaapi.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamentoPorMes;
import com.farmacia.farmaciaapi.dto.EstatisticaSaidaMedicamentoPorPaciente;
import com.farmacia.farmaciaapi.repository.ItemSaidaMedicamentoRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class SaidaDeMedicamentoService {
	
	@Autowired
	private ItemSaidaMedicamentoRepository itemSaidaMedicamentoRepository; 
	
	public byte[] relatorioPorMes() throws Exception {
		List<EstatisticaSaidaMedicamentoPorPaciente> dados = itemSaidaMedicamentoRepository.porMes(LocalDate.now());
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/saida_de_medicamentos_por_paciente.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
