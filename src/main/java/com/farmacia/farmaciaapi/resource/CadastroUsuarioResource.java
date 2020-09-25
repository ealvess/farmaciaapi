package com.farmacia.farmaciaapi.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.model.Usuario;
import com.farmacia.farmaciaapi.repository.UsuarioRepository;

@RestController
@RequestMapping("/cadastrar")
public class CadastroUsuarioResource {

		@Autowired
		private UsuarioRepository usuarioRepository;

		@Autowired
		private ApplicationEventPublisher publisher;


		@PostMapping
		public ResponseEntity<Usuario> criar(@Validated @RequestBody Usuario usuario, HttpServletResponse response) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(usuario.getSenha());
			usuario.setSenha(encodedPassword);
			Usuario usuarioSalvo = usuarioRepository.save(usuario);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));
			;
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
		}

}
