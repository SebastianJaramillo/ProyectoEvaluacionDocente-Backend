package ec.edu.espe.microserviciodocente.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.microserviciodocente.model.Docente;

public interface DocenteRepository extends CrudRepository<Docente, String> {
    
    List<Docente> findAllByOrderByApellidos();
}
