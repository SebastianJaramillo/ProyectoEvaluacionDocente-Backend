package ec.edu.espe.microserviciodocente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.microserviciodocente.model.DocenteFuncion;

public interface DocenteFuncionRepository extends CrudRepository<DocenteFuncion, Long> {
    List<DocenteFuncion> findByDocIdAndEstadoOrderByFuncId(String docId, String estado);

    List<DocenteFuncion> findByFuncIdAndEstado(String funcId, String estado);

    Optional<DocenteFuncion> findByDocIdAndFuncId(String docId, String funcId);

    List<DocenteFuncion> findByDocId(String docId);
    
    List<DocenteFuncion> findByEstado(String estado);

    List<DocenteFuncion> findByFuncIdEndingWithAndEstado(String funcId, String estado);
}
