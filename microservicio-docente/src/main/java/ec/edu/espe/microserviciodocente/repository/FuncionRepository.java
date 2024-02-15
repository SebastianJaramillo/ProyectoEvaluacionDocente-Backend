package ec.edu.espe.microserviciodocente.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.microserviciodocente.model.Funcion;
import java.util.List;



public interface FuncionRepository extends CrudRepository<Funcion, String> {
    
    Optional<Funcion> findByDescripcionAndRol(String descripcion, String rol);

    List<Funcion> findByDescripcion(String descripcion);

    List<Funcion> findByRol(String rol);
}
