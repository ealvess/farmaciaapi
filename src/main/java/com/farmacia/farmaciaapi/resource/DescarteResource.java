package com.farmacia.farmaciaapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.model.Descarte;
import com.farmacia.farmaciaapi.repository.DescarteRepository;

@RestController
@RequestMapping("/descartes")
public class DescarteResource {

	@Autowired
	private DescarteRepository descarteRepository;
	
	@GetMapping
	public List<Descarte> list(){
		return descarteRepository.findAll();
	}
}
