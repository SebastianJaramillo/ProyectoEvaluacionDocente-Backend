package ec.edu.espe.microserviciodocente.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.microserviciodocente.model.DocenteEvaluacion;

public interface DocenteEvaluacionRepository extends CrudRepository<DocenteEvaluacion, Long> {

    List<DocenteEvaluacion> findByDocEvaluador(String docEvaluador);

    List<DocenteEvaluacion> findByDocEvaluado(String docEvaluado);

    List<DocenteEvaluacion> findByDocEvaluadorAndDocEvaluadoAndEvalId(String docEvaluador, String docEvaluado, Long evalId);
}
