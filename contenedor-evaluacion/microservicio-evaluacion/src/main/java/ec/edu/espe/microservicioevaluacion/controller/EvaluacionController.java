package ec.edu.espe.microservicioevaluacion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.microservicioevaluacion.domain.Evaluacion;
import ec.edu.espe.microservicioevaluacion.service.EvaluacionService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/evaluacion")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Evaluacion>> listAll() {
        return ResponseEntity.ok().body(evaluacionService.listAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Evaluacion> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(evaluacionService.findById(Long.parseLong(id)));
    }

    @PostMapping("/registro")
    public ResponseEntity<Evaluacion> save(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.ok().body(evaluacionService.save(evaluacion));
    }

    @GetMapping("/buscar/fechas")
    public ResponseEntity<Evaluacion> findByFecha() {
        return ResponseEntity.ok().body(evaluacionService.findByFecha());
    }

    @PutMapping("/estado/{id}")
    public  ResponseEntity<Evaluacion> updateEstado(@PathVariable String id, @RequestBody String estado) {

        return ResponseEntity.ok().body(evaluacionService.updateEstado(Long.parseLong(id), estado));
  
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarById(@PathVariable String id) {
        this.evaluacionService.eliminarById(Long.parseLong(id));
         return ResponseEntity.ok().build();

    }
       
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
    Evaluacion evaluacionActualizada = evaluacionService.updateEvaluacion(id, evaluacion);
    return ResponseEntity.ok().body(evaluacionActualizada);
}
    
}
