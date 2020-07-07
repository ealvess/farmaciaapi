package com.farmacia.farmaciaapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.model.Descarte;

@RestController
@RequestMapping("/emprestimos")
public class FarmaciaResource {
	
	@Autowired
	private DescarteRepository descarteRepository;
	
	@GetMapping
	public List<Descarte> listar(){
		return descarteRepository.findAll();
	}
}
