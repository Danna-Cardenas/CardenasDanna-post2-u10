package com.cardenas.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardenas.todo.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByCompletada(boolean completada);
}
