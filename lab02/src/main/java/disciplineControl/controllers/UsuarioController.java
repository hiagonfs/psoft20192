package disciplineControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import disciplineControl.entities.Usuario;
import disciplineControl.services.UsuarioService;

public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/v1/auth/usuarios")
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.cadastrarUsuario(usuario), HttpStatus.CREATED);
	}

}
