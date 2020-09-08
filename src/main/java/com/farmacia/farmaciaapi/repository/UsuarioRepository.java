package com.farmacia.farmaciaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Usuario;
import com.farmacia.farmaciaapi.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{

	public Optional<Usuario> findByEmail(String email);
}
