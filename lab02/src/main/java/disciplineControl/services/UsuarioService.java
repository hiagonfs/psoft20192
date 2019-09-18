package disciplineControl.services;

import disciplineControl.entities.Usuario;
import disciplineControl.repository.UsuarioRepository;

public class UsuarioService {

	private UsuarioRepository<Usuario, String> usuarioRepositoryDAO;

	public UsuarioService(UsuarioRepository<Usuario, String> usuariosDAO) {
		this.usuarioRepositoryDAO = usuariosDAO;
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		return this.usuarioRepositoryDAO.save(usuario);
	}

}
