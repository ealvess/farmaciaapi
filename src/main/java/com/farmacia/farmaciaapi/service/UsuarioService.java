package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Usuario;
import com.farmacia.farmaciaapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario atualizar(Long codigo, Usuario usuario) {

		Usuario usuarioSalvo = buscarUsuarioSalvo(codigo);

		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");

		return this.usuarioRepository.save(usuarioSalvo);
	}
	

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Usuario usuarioSalvo = buscarUsuarioSalvo(codigo);
		usuarioSalvo.setAtivo(ativo);
		usuarioRepository.save(usuarioSalvo);
	}
	

	public Usuario buscarUsuarioSalvo(Long codigo) {
		Usuario usuarioSalvo = this.usuarioRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return usuarioSalvo;
	}



}
