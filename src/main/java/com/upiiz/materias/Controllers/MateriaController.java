package com.upiiz.materias.Controllers;

import com.upiiz.materias.Models.Materia;
import com.upiiz.materias.dto.MateriaDTO;
import com.upiiz.materias.Services.MateriasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    private final MateriasService materiasService;

    public MateriaController(MateriasService materiasService) {
        this.materiasService = materiasService;
    }

    // 1. Ver listado
    @GetMapping
    public String listarMaterias(Model model) {
        model.addAttribute("materias", materiasService.obtenerTodas());
        return "Listado"; 
    }

    // 2. Crear nueva materia
    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("materiaDTO", new MateriaDTO());
        return "Crear"; 
    }

    @PostMapping("/guardar")
    public String guardarMateria(@ModelAttribute("materiaDTO") MateriaDTO materiaDTO) {
        materiasService.guardar(materiaDTO);
        return "redirect:/materias";
    }

    // 3. Editar materia
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Materia materia = materiasService.obtenerPorId(id);
        
        if (materia == null) {
            return "redirect:/materias"; 
        }
        
        // Enviamos todo lo necesario a la vista para evitar el error 500
        MateriaDTO dto = new MateriaDTO(materia.getNombre(), materia.getCreditos());
        model.addAttribute("materiaDTO", dto); // Para llenar el formulario
        model.addAttribute("materiaId", id);   // Para la ruta de actualización
        model.addAttribute("materia", materia); // Red de seguridad por si el HTML lo pide
        
        return "Actualizar"; 
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMateria(@PathVariable Long id, @ModelAttribute("materiaDTO") MateriaDTO materiaDTO) {
        materiasService.actualizar(id, materiaDTO);
        return "redirect:/materias";
    }

    // 4. Confirmar eliminación
    @GetMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable Long id, Model model) {
        Materia materia = materiasService.obtenerPorId(id);
        
        if (materia == null) {
            return "redirect:/materias"; 
        }
        
        // Enviamos el objeto completo para que Thymeleaf pueda leer su nombre e ID
        model.addAttribute("materia", materia);
        
        return "Eliminar"; 
    }

    @PostMapping("/borrar/{id}")
    public String borrarMateria(@PathVariable Long id) {
        materiasService.eliminar(id);
        return "redirect:/materias";
    }
}