package disciplineControl.controllers;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplineControl.entities.Usuario;
import disciplineControl.services.UsuarioService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private final String TOKEN_KEY = "minha_senha";

	private UsuarioService usuarioService;

	public LoginController(UsuarioService uService) {
		super();
		this.usuarioService = uService;
	}

	public LoginResponse autenticacao(@RequestBody Usuario usuario) throws ServletException {

		Optional<Usuario> usuarioRecuperado = usuarioService.getUsuarioById(usuario.getEmail());

		verificaUsuarioNoSistema(usuarioRecuperado);

		verificaSenha(usuario, usuarioRecuperado);

		String tokenGerado = Jwts.builder().setSubject(usuarioRecuperado.get().getEmail())
				.signWith(SignatureAlgorithm.ES512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();

		return new LoginResponse(tokenGerado);

	}

	private void verificaUsuarioNoSistema(Optional<Usuario> usuarioRecuperado) throws ServletException {
		if (usuarioRecuperado.isEmpty()) {
			throw new ServletException("Usuario nao encontrado no sistema!");
		}
	}

	private void verificaSenha(Usuario usuario, Optional<Usuario> usuarioRecuperado) throws ServletException {
		if (!usuario.getSenha().equals(usuarioRecuperado.get().getSenha())) {
			throw new ServletException("Senha invalida!");
		}
	}

	private class LoginResponse {

		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}

	}

}