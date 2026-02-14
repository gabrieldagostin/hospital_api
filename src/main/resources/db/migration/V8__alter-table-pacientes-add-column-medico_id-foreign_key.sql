ALTER TABLE pacientes
ADD COLUMN medico_id UUID,
ADD CONSTRAINT fk_pacientes_medico
    FOREIGN KEY (medico_id) REFERENCES medicos(id) ON DELETE SET NULL;
