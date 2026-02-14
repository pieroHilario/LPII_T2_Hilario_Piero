package com.cibertec.LPII_T2_Hilario_Piero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.LPII_T2_Hilario_Piero.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}