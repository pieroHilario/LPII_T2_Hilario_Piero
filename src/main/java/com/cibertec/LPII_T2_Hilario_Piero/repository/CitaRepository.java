package com.cibertec.LPII_T2_Hilario_Piero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cibertec.LPII_T2_Hilario_Piero.model.Cita;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    
    @Query("SELECT c FROM Cita c JOIN FETCH c.paciente JOIN FETCH c.medico ORDER BY c.fecha DESC")
    List<Cita> findAllWithDetails();
}