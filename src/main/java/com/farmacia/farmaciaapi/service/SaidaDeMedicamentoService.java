package com.farmacia.farmaciaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.repository.ItemSaidaMedicamentoRepository;

@Service
public class SaidaDeMedicamentoService {
	
	@Autowired
	private ItemSaidaMedicamentoRepository itemSaida; 
	
	/*public byte[] relatorioPorPessoa(LocalDate fim, LocalDate inicio) throws Exception {
		List<EstatisticaSaidaMedicamentoPorPaciente> dados = itemSaida.porMes(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/saida-medicamento-por-pessoa.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}*/

}
