package com.cardenas.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TareasViewController {
    @GetMapping("/tareas")
    public String tareas() {
        return "forward:/tareas.html";
    }

    @GetMapping("/tareas/nueva")
    public String nuevaTarea() {
        return "forward:/nueva-tarea.html";
    }
}
