package com.farmacia.farmaciaapi.repository.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Usuario;
import com.farmacia.farmaciaapi.repository.filter.UsuarioFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoUsuario;

public interface UsuarioRepositoryQuery {
	
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
	public Page<ResumoUsuario> resumo(UsuarioFilter UsuarioFilter, Pageable pageable);
}
