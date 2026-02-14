package com.cibertec.LPII_T2_Hilario_Piero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.LPII_T2_Hilario_Piero.model.*;
import com.cibertec.LPII_T2_Hilario_Piero.service.CitaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/consulta";
    }

    @GetMapping("/consulta")
    public String consultaCitas(Model model) {
        try {
            List<Cita> citas = citaService.listarTodasLasCitas();
            model.addAttribute("citas", citas);
            return "consultaCita";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar citas: " + e.getMessage());
            return "consultaCita";
        }
    }

    @GetMapping("/mantenimiento")
    public String mantenimientoCita(@RequestParam(required = false) Integer idCita, Model model) {
        try {
            List<Paciente> pacientes = citaService.listarPacientes();
            List<Medico> medicos = citaService.listarMedicos();
            
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            
            if (idCita != null) {
                Optional<Cita> citaOpt = citaService.buscarCitaPorId(idCita);
                if (citaOpt.isPresent()) {
                    model.addAttribute("cita", citaOpt.get());
                } else {
                    model.addAttribute("error", "Cita no encontrada");
                    Cita nuevaCita = new Cita();
                    nuevaCita.setFecha(LocalDate.now());
                    nuevaCita.setCosto(0.0);
                    model.addAttribute("cita", nuevaCita);
                }
            } else {
                Cita nuevaCita = new Cita();
                nuevaCita.setFecha(LocalDate.now());
                nuevaCita.setCosto(0.0);
                model.addAttribute("cita", nuevaCita);
            }
            
            return "mantenimientoCita";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar datos: " + e.getMessage());
            return "mantenimientoCita";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarCita(
            @RequestParam(required = false) Integer idCita,
            @RequestParam Integer idPaciente,
            @RequestParam Integer idMedico,
            @RequestParam String fecha,
            @RequestParam Double costo,
            RedirectAttributes redirectAttributes) {
        
        try {
            Cita cita;
            
            if (idCita != null && idCita > 0) {
                Optional<Cita> citaOpt = citaService.buscarCitaPorId(idCita);
                if (citaOpt.isPresent()) {
                    cita = citaOpt.get();
                } else {
                    redirectAttributes.addFlashAttribute("error", "ERROR: Cita no encontrada");
                    return "redirect:/consulta";
                }
            } else {
                cita = new Cita();
            }
            
            Optional<Paciente> pacienteOpt = citaService.listarPacientes().stream()
                .filter(p -> p.getIdPaciente().equals(idPaciente))
                .findFirst();
            
            Optional<Medico> medicoOpt = citaService.listarMedicos().stream()
                .filter(m -> m.getIdMedico().equals(idMedico))
                .findFirst();
            
            if (pacienteOpt.isEmpty() || medicoOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "ERROR: Paciente o médico no encontrado");
                return "redirect:/mantenimiento";
            }
            
            cita.setPaciente(pacienteOpt.get());
            cita.setMedico(medicoOpt.get());
            cita.setFecha(LocalDate.parse(fecha));
            cita.setCosto(costo);
            
            citaService.guardarCita(cita);
            
            String mensaje = (idCita != null) 
                ? "✅ Cita N° " + cita.getIdCita() + " actualizada exitosamente"
                : "✅ Cita N° " + cita.getIdCita() + " creada exitosamente";
            
            redirectAttributes.addFlashAttribute("exito", mensaje);
            return "redirect:/consulta";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "ERROR al procesar cita: " + e.getMessage());
            return "redirect:/mantenimiento";
        }
    }
}