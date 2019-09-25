package disciplineControl.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplineControl.entities.Usuario;
import disciplineControl.services.JwtService;
import disciplineControl.services.UsuarioService;

@RestController
public class UsuarioController {

	private UsuarioService usuarioService;
	private JwtService jwtService;

	public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {
		super();
		this.usuarioService = usuarioService;
		this.jwtService = jwtService;
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.cadastrarUsuario(usuario), HttpStatus.CREATED);
	}

	@DeleteMapping("/auth/usuarios/{email}")
	public ResponseEntity<Usuario> excluirCadastro(@PathVariable String email,
			@RequestHeader("Authorization") String header) {

		if (!usuarioService.getUsuarioById(email).isPresent()) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		try {
			if (jwtService.usuarioTemPermissao(email, header)) {
				return new ResponseEntity<Usuario>(usuarioService.excluirUsuario(email), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/usuarios/{email}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable String email) {
		return new ResponseEntity<Usuario>(usuarioService.getUsuarioById(email).get(), HttpStatus.OK);
	}

}
