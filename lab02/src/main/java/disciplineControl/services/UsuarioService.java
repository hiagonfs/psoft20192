package disciplineControl.services;

import org.springframework.stereotype.Service;

import disciplineControl.entities.Usuario;
import disciplineControl.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository<Usuario, String> usuarioRepositoryDAO;

	public UsuarioService(UsuarioRepository<Usuario, String> usuariosDAO) {
		super(); 
		this.usuarioRepositoryDAO = usuariosDAO;
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		return this.usuarioRepositoryDAO.save(usuario);
	}

}
