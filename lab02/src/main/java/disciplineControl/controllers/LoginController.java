package disciplineControl.controllers;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplineControl.entities.Usuario;
import disciplineControl.services.JwtService;
import disciplineControl.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private UsuarioService usuarioService;
	private JwtService jwtService;

	public LoginController(UsuarioService uService, JwtService jwtService) {
		super();
		this.usuarioService = uService;
		this.jwtService = jwtService;
	}

	/**
	 * Este método recupera as credenciais do usuário no corpo da requisição HTTP,
	 * verifica se o usuário existe e se a senha passada bate com a senha do usuário
	 * na base de dados.
	 * 
	 * @param usuario
	 * @return token gerado pela autenticacao
	 * @throws ServletException
	 */
	@PostMapping("/login")
	public LoginResponse autenticacao(@RequestBody String email) throws ServletException {

		Optional<Usuario> usuarioRecuperado = usuarioService.getUsuarioById(email);

		verificaUsuario(usuarioRecuperado);

		verificaSenha(email, usuarioRecuperado);

		String tokenGerado = jwtService.geraToken(email);

		return new LoginResponse(tokenGerado);

	}

	private void verificaUsuario(Optional<Usuario> usuarioRecuperado) throws ServletException {
		if (!usuarioRecuperado.isPresent()) {
			throw new ServletException("Usuario nao encontrado no sistema!");
		}
	}

	private void verificaSenha(String email, Optional<Usuario> usuarioRecuperado) throws ServletException {
		if (!email.equals(usuarioRecuperado.get().getSenha())) {
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