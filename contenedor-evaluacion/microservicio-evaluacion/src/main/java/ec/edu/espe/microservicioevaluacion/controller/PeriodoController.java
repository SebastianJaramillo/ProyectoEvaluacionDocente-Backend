package ec.edu.espe.microservicioevaluacion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.microservicioevaluacion.model.Periodo;
import ec.edu.espe.microservicioevaluacion.service.PeriodoService;

@CrossOrigin
@RestController
@RequestMapping("/periodo")
public class PeriodoController {
    
    private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Periodo>> listAllActivo() {
        return ResponseEntity.ok().body(this.periodoService.findByEstado("ACTIVO"));
    }

    @GetMapping
    public ResponseEntity<Iterable<Periodo>> listAll() {
        return ResponseEntity.ok().body(this.periodoService.listAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Periodo> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.periodoService.findById(id));
    }

    @PostMapping("/registro")
    public ResponseEntity<Periodo> save(@RequestBody Periodo periodo) {
        return ResponseEntity.ok().body(this.periodoService.save(periodo));
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<Periodo> updateEstado(@PathVariable Long id, @RequestBody String estado) {
        return ResponseEntity.ok().body(this.periodoService.updateEstado(id, estado));
    }
}
