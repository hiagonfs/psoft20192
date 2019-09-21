package disciplineControl.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplineControl.entities.Usuario;
import disciplineControl.services.UsuarioService;

@RestController
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@PostMapping("/v1/auth/usuarios")
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.cadastrarUsuario(usuario), HttpStatus.CREATED);
	}

	@DeleteMapping("/auth/usuarios/")
	public ResponseEntity<Usuario> excluirCadastro(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.excluirUsuario(usuario), HttpStatus.OK);
	}

	@RequestMapping("/usuarios/{email}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable String email) {
		return new ResponseEntity<Usuario>(usuarioService.getUsuarioById(email).get(), HttpStatus.OK);
	}

	@RequestMapping("/v1/auth/usuarios")
	public ResponseEntity<List<Usuario>> retornaUsuariosCadastrados() {
		return new ResponseEntity<List<Usuario>>(usuarioService.getUsuarios(), HttpStatus.OK);
	}

}
