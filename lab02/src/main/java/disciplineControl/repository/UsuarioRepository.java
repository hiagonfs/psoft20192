package disciplineControl.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import disciplineControl.entities.Usuario;

public interface UsuarioRepository<T, ID extends Serializable> extends JpaRepository<Usuario, String>{

}
