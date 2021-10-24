package es.springboot.bci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.springboot.bci.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findByEmail(String email);
	
}
