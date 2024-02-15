package ec.edu.espe.microserviciodocente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.microserviciodocente.model.Funcion;
import ec.edu.espe.microserviciodocente.repository.FuncionRepository;

@Service
public class FuncionService {
    
    private final FuncionRepository funcionRepository;

    public FuncionService(FuncionRepository funcionRepository) {
        this.funcionRepository = funcionRepository;
    }

    public Funcion save(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    public Iterable<Funcion> listAll() {
        return this.funcionRepository.findAll();
    }

    public Funcion findById(String id) {
        Optional<Funcion> optionalFuncion = this.funcionRepository.findById(id);

        if (optionalFuncion.isPresent()) {
            return optionalFuncion.get();
        }

        throw new RuntimeException("Función con id: " + id + " no se encuentra.");
    }

    public Funcion findByDescripcionAndRol(String descripcion, String rol) {
        Optional<Funcion> optionalFuncion = this.funcionRepository.findByDescripcionAndRol(descripcion, rol);

        if (optionalFuncion.isPresent()) {
            return optionalFuncion.get();
        }

        throw new RuntimeException("Función con descripcion: " + descripcion + " y rol: " + rol + " no existe.");
    }

    public List<Funcion> findByRol(String rol) {
        return this.funcionRepository.findByRol(rol);
    }

    public List<Funcion> findByDescripcion(String descripcion) {
        return this.funcionRepository.findByDescripcion(descripcion);
    }
}
