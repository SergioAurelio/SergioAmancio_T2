package com.example.laboratorio4.service;

import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.repository.AlumnoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlumnoService {

    private final AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public List<Alumno> listar() {
        return repository.findAll();
    }

    public Alumno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alumno no encontrado"
                ));
    }

    public Alumno registrar(Alumno alumno) {
        alumno.setId(null);
        return repository.save(alumno);
    }

    public Alumno actualizar(Long id, Alumno datos) {
        Alumno alumno = buscarPorId(id);

        alumno.setNombres(datos.getNombres());
        alumno.setCarrera(datos.getCarrera());
        alumno.setCiclo(datos.getCiclo());
        alumno.setPromedio(datos.getPromedio());

        return repository.save(alumno);
    }

    public void eliminar(Long id) {
        Alumno alumno = buscarPorId(id);
        repository.delete(alumno);
    }
}