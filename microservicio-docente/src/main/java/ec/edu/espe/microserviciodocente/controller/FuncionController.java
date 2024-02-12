package ec.edu.espe.microserviciodocente.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.microserviciodocente.model.DocenteFuncion;
import ec.edu.espe.microserviciodocente.model.Funcion;
import ec.edu.espe.microserviciodocente.service.DocenteFuncionService;
import ec.edu.espe.microserviciodocente.service.FuncionService;

@RestController
@RequestMapping("/funcion")
@CrossOrigin
public class FuncionController {

    private final FuncionService funcionService;
    private final DocenteFuncionService docenteFuncionService;

    public FuncionController(FuncionService funcionService, DocenteFuncionService docenteFuncionService) {
        this.funcionService = funcionService;
        this.docenteFuncionService = docenteFuncionService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Funcion>> listAll() {
        return ResponseEntity.ok().body(funcionService.listAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Funcion> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(funcionService.findById(id));
    }

    @PostMapping("/registro")
    public ResponseEntity<Funcion> save(@RequestBody Funcion funcion) {
        return ResponseEntity.ok().body(funcionService.save(funcion));
    }

    @GetMapping("/docente/{id}")
    public ResponseEntity<List<DocenteFuncion>> findByDocente(@PathVariable String id) {
        return ResponseEntity.ok().body(this.docenteFuncionService.findByDocente(id));
    }

    @GetMapping("/{descripcion}/{rol}")
    public ResponseEntity<Funcion> findByDescripcionRol(@PathVariable String descripcion, @PathVariable String rol) {
        return ResponseEntity.ok().body(this.funcionService.findByDescripcionAndRol(descripcion, rol));
    }

    @GetMapping("/descripcion/{descripcion}")
    public ResponseEntity<List<Funcion>> findByDescripcion(@PathVariable String descripcion) {
        return ResponseEntity.ok().body(this.funcionService.findByDescripcion(descripcion));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Funcion>> findByRol(@PathVariable String rol) {
        return ResponseEntity.ok().body(this.funcionService.findByRol(rol));
    }
}