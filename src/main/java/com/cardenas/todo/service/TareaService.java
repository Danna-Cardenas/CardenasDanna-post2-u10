package com.cardenas.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cardenas.todo.entity.Tarea;
import com.cardenas.todo.repository.TareaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TareaService {
    private final TareaRepository repo;

    public TareaService(TareaRepository repo) {
        this.repo = repo;
    }

    public Tarea crear(Tarea tarea) {
        if (tarea.getTitulo() == null || tarea.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El titulo no puede estar vacio");
        }
        return repo.save(tarea);
    }

    public List<Tarea> listar() {
        return repo.findAll();
    }

    public Tarea buscarPorId(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada: " + id));
    }

    public Tarea completar(Long id) {
        Tarea tarea = buscarPorId(id);
        tarea.setCompletada(true);
        return repo.save(tarea);
    }
}
