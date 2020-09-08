package com.farmacia.farmaciaapi.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaapi.event.RecursoCriadoEvent;
import com.farmacia.farmaciaapi.model.Permissao;
import com.farmacia.farmaciaapi.model.Usuario;
import com.farmacia.farmaciaapi.repository.UsuarioRepository;
import com.farmacia.farmaciaapi.repository.filter.UsuarioFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoUsuario;
import com.farmacia.farmaciaapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		return usuarioRepository.filtrar(usuarioFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
	public Page<ResumoUsuario> resumo(UsuarioFilter usuarioFilter, Pageable pageable) {
		return usuarioRepository.resumo(usuarioFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO')")
	public ResponseEntity<Usuario> criar(@Validated @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		if (usuarioSalvo.getTipo() == "farmaceutico") {
			List<Permissao> permissoes = new ArrayList<Permissao>();
			permissoes.add('1', null);
			usuarioSalvo.setPermissoes(permissoes);
		}

		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.usuarioRepository.findById(codigo).map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO')")
	public void remover(@PathVariable Long codigo) {
		usuarioRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Validated @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}

	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		usuarioService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
