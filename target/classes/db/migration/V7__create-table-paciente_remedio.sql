CREATE TABLE paciente_remedio (
    paciente_id BIGINT NOT NULL,
    remedio_id BIGINT NOT NULL,
    PRIMARY KEY (paciente_id, remedio_id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE SET NULL,
    FOREIGN KEY (remedio_id) REFERENCES remedios(id) ON DELETE SET NULL
);
