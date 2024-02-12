package ec.edu.espe.microservicioevaluacion.service;

import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;

import ec.edu.espe.microservicioevaluacion.domain.Evaluacion;
import ec.edu.espe.microservicioevaluacion.repository.EvaluacionRepository;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public Evaluacion save(Evaluacion evaluacion) {
        try {
            return this.evaluacionRepository.save(evaluacion);
        } catch (Exception e) {
            throw new RuntimeException("Evaluación con ID: " + evaluacion.getId() + " no se encuentra.");
        }
        
    }

    public Iterable<Evaluacion> listAll() {
        return this.evaluacionRepository.findAll();
    }

    public Evaluacion findById(long id) {
        Optional<Evaluacion> optionalevaluacion = this.evaluacionRepository.findById(id);

        if(optionalevaluacion.isPresent()) {
            return optionalevaluacion.get();
        }

        throw new RuntimeException("evaluacion con ID: " + id + " no se encuentra.");
    }

    public Evaluacion findByFecha() {
        Optional<Evaluacion> optionalevaluacion = this.evaluacionRepository.findByEvalFechaInicioLessThanEqualAndEvalFechaFinGreaterThanEqual(new Date(), new Date());

        if(optionalevaluacion.isPresent()) {
            return optionalevaluacion.get();
        }

        throw new RuntimeException("No se encontró evaluación en esta fecha actual");
    }

    public Evaluacion updateEstado(long id, String nuevoEstado) {
        // Primero, encuentra la evaluación por ID
        Optional<Evaluacion> evaluacionOptional = this.evaluacionRepository.findById(id);
    
        if (evaluacionOptional.isPresent()) {
            Evaluacion evaluacion = evaluacionOptional.get();
    
            // Actualiza el estado de la evaluación
            evaluacion.setEstado(nuevoEstado); // Asume que tienes un setter para el campo estado en tu entidad
    
            // Guarda la evaluación con el estado actualizado
            return this.evaluacionRepository.save(evaluacion);
        } else {
            throw new RuntimeException("Evaluación con ID: " + id + " no se encuentra.");
        }
    }

    public void eliminarById(Long id) {
        if (this.evaluacionRepository.existsById(id)) {
            this.evaluacionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Formulario no encontrado con ID: " + id);
        }
    }
    
    public Evaluacion updateEvaluacion(Long id, Evaluacion evaluacionActualizada) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluacion con ID: " + id + " no se encuentra."));
    
        // Actualiza los campos de la evaluacion
        evaluacion.setEvalFechaInicio(evaluacionActualizada.getEvalFechaInicio());
        evaluacion.setEval_fecha_Fin(evaluacionActualizada.getEvalFechaFin());
        evaluacion.setPerId(evaluacionActualizada.getPerId());
        // Añade más campos según sea necesario
    
        return evaluacionRepository.save(evaluacion);
    }
}