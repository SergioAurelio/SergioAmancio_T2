package com.example.laboratorio4.controller;

import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.service.AlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Alumno> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Alumno buscar(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Alumno> registrar(
            @RequestBody Alumno alumno) {

        Alumno creado = service.registrar(alumno);

        return ResponseEntity
                .created(URI.create("/api/alumnos/" + creado.getId()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public Alumno actualizar(
            @PathVariable("id") Long id,
            @RequestBody Alumno alumno) {

        return service.actualizar(id, alumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Long id) {

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}