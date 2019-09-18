package disciplineControl.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/**@PostMapping("/auth/login")
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.logar(usuario), HttpStatus.OK);
	}**/
	
	@DeleteMapping("/auth/usuarios/")
	public ResponseEntity<Usuario> excluirCadastro(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.excluirUsuario(usuario), HttpStatus.OK); 
	}
	

}
