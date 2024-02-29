package ec.edu.espe.microserviciodocente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.microserviciodocente.model.Docente;
import ec.edu.espe.microserviciodocente.model.DocenteFuncion;
import ec.edu.espe.microserviciodocente.model.Funcion;
import ec.edu.espe.microserviciodocente.repository.DocenteFuncionRepository;
import ec.edu.espe.microserviciodocente.repository.DocenteRepository;
import ec.edu.espe.microserviciodocente.repository.FuncionRepository;

@Service
public class DocenteFuncionService {
    private final DocenteFuncionRepository docenteFuncionRepository;
    private final DocenteRepository docenteRepository;
    private final FuncionRepository funcionRepository;

    public DocenteFuncionService(DocenteFuncionRepository docenteFuncionRepository,
            DocenteRepository docenteRepository, FuncionRepository funcionRepository) {
        this.docenteFuncionRepository = docenteFuncionRepository;
        this.docenteRepository = docenteRepository;
        this.funcionRepository = funcionRepository;
    }

    public List<DocenteFuncion> findByDocente(String docId) {
        return docenteFuncionRepository.findByDocIdAndEstadoOrderByFuncId(docId, "ACTIVO");
    }

    public List<DocenteFuncion> findByFuncion(String funcId) {
        return docenteFuncionRepository.findByFuncIdAndEstado(funcId, "ACTIVO");
    }

    public List<DocenteFuncion> findByContainingFuncion(String funcId) {
        return docenteFuncionRepository.findByFuncIdEndingWithAndEstado(funcId, "ACTIVO");
    }

    public List<DocenteFuncion> findByContainingFuncionTodos(String funcId) {
        return docenteFuncionRepository.findByFuncIdEndingWith(funcId);
    }

    public List<DocenteFuncion> findByContainingFuncionStart(String docId) {
        return docenteFuncionRepository.findByFuncIdStartingWithAndEstadoAndDocId("DIR", "ACTIVO", docId);
    }

    public DocenteFuncion addDocenteFuncion(DocenteFuncion docenteFuncion) {
        Optional<Funcion> optionalFuncion = this.funcionRepository.findById(docenteFuncion.getFuncId());

        if (!optionalFuncion.isPresent()) {
            throw new RuntimeException("Funcion con ID: " + docenteFuncion.getFuncId() + " no existe.");
        }
    
        Optional<Docente> optionalDocente = this.docenteRepository.findById(docenteFuncion.getDocId());
        if (!optionalDocente.isPresent()) {
            throw new RuntimeException("Docente con ID: " + docenteFuncion.getDocId() + " no existe.");
        }
    
        Optional<DocenteFuncion> optionalDocenteFuncion = this.docenteFuncionRepository
                .findByDocIdAndFuncId(docenteFuncion.getDocId(), docenteFuncion.getFuncId());
    
        if (optionalDocenteFuncion.isPresent()) {
            DocenteFuncion existenteDocenteFuncion = optionalDocenteFuncion.get();
            existenteDocenteFuncion.setEstado("ACTIVO");
            return this.docenteFuncionRepository.save(existenteDocenteFuncion);
        } else {
            docenteFuncion.setEstado("ACTIVO");
            return this.docenteFuncionRepository.save(docenteFuncion);
        }
    }

    public DocenteFuncion updateEstado(Long id) {
        Optional<DocenteFuncion> optionalDocenteFuncion = this.docenteFuncionRepository.findById(id);

        if (optionalDocenteFuncion.isPresent()) {
            optionalDocenteFuncion.get().setEstado("INACTIVO");
            return this.docenteFuncionRepository.save(optionalDocenteFuncion.get());
        } else {
            throw new RuntimeException("No se pudo encontrar docente con esa funcion.");
        }
    }

    public Iterable<DocenteFuncion> listAll() {
        return docenteFuncionRepository.findAll();
    }

    public void updateFuncion(String id) {
        Iterable<DocenteFuncion> docenteFunciones = this.docenteFuncionRepository.findByDocId(id);
    
        if (docenteFunciones.iterator().hasNext()) {
            for (DocenteFuncion docenteFuncion : docenteFunciones) {
                docenteFuncion.setEstado("INACTIVO");
                this.docenteFuncionRepository.save(docenteFuncion);
            }
        } else {
            throw new RuntimeException("No se pudo encontrar docente con esa funcion.");
        }
    }

    public void eliminarByDocId(String docId) {
        Iterable<DocenteFuncion> docenteFunciones = docenteFuncionRepository.findByDocId(docId);

        if (docenteFunciones.iterator().hasNext()) {
            docenteFuncionRepository.deleteAll(docenteFunciones);
        } else {
            throw new RuntimeException("No se encontraron funciones para el docente con ID: " + docId);
        }
    }

    public DocenteFuncion save(DocenteFuncion funcion) {
        return docenteFuncionRepository.save(funcion);
    }
    public DocenteFuncion inactivarDocenteFuncion(DocenteFuncion docenteFuncion) {
        String docId = docenteFuncion.getDocId();
        String funcId = docenteFuncion.getFuncId();
    
        Optional<DocenteFuncion> optionalDocenteFuncion = this.docenteFuncionRepository.findByDocIdAndFuncId(docId, funcId);
    
        if (optionalDocenteFuncion.isPresent()) {
            DocenteFuncion existenteDocenteFuncion = optionalDocenteFuncion.get();
            existenteDocenteFuncion.setEstado("INACTIVO");
            return this.docenteFuncionRepository.save(existenteDocenteFuncion);
        } else {
            throw new RuntimeException("No se encontró la relación entre Docente con ID: " + docId
                    + " y Función con ID: " + funcId + ".");
        }
    }

    public Iterable<DocenteFuncion> listAllActive() {
        return docenteFuncionRepository.findByEstado("ACTIVO");
    }

    public DocenteFuncion findById(Long id) {
        Optional<DocenteFuncion> optionalDocente = docenteFuncionRepository.findById(id);

        if (optionalDocente.isPresent()) {
            return optionalDocente.get();
        }

        throw new RuntimeException("Docente con ID: " + id + " no se encuentra.");
    }

}
