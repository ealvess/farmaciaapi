package com.farmacia.farmaciaapi.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.dto.EstatisticaSaidaMedicamentoPorPaciente;
import com.farmacia.farmaciaapi.repository.ItemSaidaMedicamentoRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class SaidaDeMedicamentoService {
	
	@Autowired
	private ItemSaidaMedicamentoRepository itemSaida; 
	
	public byte[] relatorioPorPessoa(LocalDate fim, LocalDate inicio) throws Exception {
		List<EstatisticaSaidaMedicamentoPorPaciente> dados = itemSaida.porPaciente(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/saida-medicamento-por-pessoa.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
