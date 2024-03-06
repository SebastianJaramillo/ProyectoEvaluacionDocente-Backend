package ec.edu.espe.microservicioevaluacion.repository;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.microservicioevaluacion.model.Evaluacion;


public interface EvaluacionRepository extends CrudRepository<Evaluacion, Long>{

    Optional<Evaluacion> findByEvalFechaInicioLessThanEqualAndEvalFechaFinGreaterThanEqualAndPerId(Date fechaInicio, Date fechaFin, Long perId);

    Optional<Evaluacion> findByPerId(Long perId);
}
