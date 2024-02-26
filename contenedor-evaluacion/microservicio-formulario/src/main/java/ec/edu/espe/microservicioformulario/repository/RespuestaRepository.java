package ec.edu.espe.microservicioformulario.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.microservicioformulario.model.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long>{

    List<Respuesta> findByPreIdStartingWithAndDocEvaluadoAndEvalId(String preId, String docEvaluado, Long evalId);
}
