package com.cibertec.LPII_T2_Hilario_Piero.service;

import com.cibertec.LPII_T2_Hilario_Piero.model.*;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> listarTodasLasCitas();
    Optional<Cita> buscarCitaPorId(Integer id);
    Cita guardarCita(Cita cita);
    void eliminarCita(Integer id);
    List<Paciente> listarPacientes();
    List<Medico> listarMedicos();
}