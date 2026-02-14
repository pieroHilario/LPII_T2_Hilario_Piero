package com.cibertec.LPII_T2_Hilario_Piero.service;

import org.springframework.stereotype.Service;
import com.cibertec.LPII_T2_Hilario_Piero.model.*;
import com.cibertec.LPII_T2_Hilario_Piero.repository.*;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    
    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public CitaServiceImpl(CitaRepository citaRepository,
                          PacienteRepository pacienteRepository,
                          MedicoRepository medicoRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<Cita> listarTodasLasCitas() {
        return citaRepository.findAllWithDetails();
    }

    @Override
    public Optional<Cita> buscarCitaPorId(Integer id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public void eliminarCita(Integer id) {
        citaRepository.deleteById(id);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }
}
