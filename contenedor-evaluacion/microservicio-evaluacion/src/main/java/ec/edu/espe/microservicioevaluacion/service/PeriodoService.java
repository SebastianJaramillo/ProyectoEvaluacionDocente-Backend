package ec.edu.espe.microservicioevaluacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.microservicioevaluacion.model.Periodo;
import ec.edu.espe.microservicioevaluacion.repository.PeriodoRepository;

@Service
public class PeriodoService {

    private final PeriodoRepository periodoRepository;

    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public Periodo save(Periodo periodo) {
        periodo.setEstado("ACTIVO");
        return this.periodoRepository.save(periodo);
    }

    public Iterable<Periodo> listAll() {
        return this.periodoRepository.findAll();
    }

    public Periodo findById(long id) {
        Optional<Periodo> optionalPeriodo = this.periodoRepository.findById(id);

        if(optionalPeriodo.isPresent()) {
            return optionalPeriodo.get();
        }

        throw new RuntimeException("Periodo con ID: " + id + " no se encuentra.");
    }

    public List<Periodo> findByEstado(String estado) {
        return this.periodoRepository.findByEstado(estado);
    }

    public Periodo updateEstado(Long id, String nuevoEstado) {
        Optional<Periodo> periodoOptional = this.periodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
            Periodo periodo = periodoOptional.get();

            periodo.setEstado(nuevoEstado);

            return this.periodoRepository.save(periodo);
        } else {
            throw new RuntimeException("Periodo con ID: " + id + " no se encuentra.");
        }
    }
}
