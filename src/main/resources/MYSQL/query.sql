CREATE DATABASE bd_t2_hilario;
USE bd_t2_hilario;


CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(15)
);

CREATE TABLE medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50) NOT NULL
);

CREATE TABLE cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico)
);


INSERT INTO paciente (nombres, dni, telefono) VALUES 
('Juan Carlos Pérez García', '12345678', '987654321'),
('María Elena López Torres', '23456789', '976543210'),
('Carlos Alberto Rodríguez', '34567890', '965432109');

INSERT INTO medico (nombres, especialidad) VALUES 
('Dr. Roberto García Sánchez', 'Cardiología'),
('Dra. Carmen López Flores', 'Pediatría'),
('Dr. Miguel Torres Ramírez', 'Traumatología');

INSERT INTO cita (fecha, costo, id_paciente, id_medico) VALUES 
('2026-02-15', 150.00, 1, 1),
('2026-02-16', 120.00, 2, 2),
('2026-02-17', 180.00, 3, 3);

SHOW DATABASES LIKE 'bd_t2_hilario';