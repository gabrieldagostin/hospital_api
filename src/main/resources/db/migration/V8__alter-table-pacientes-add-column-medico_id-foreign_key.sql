ALTER TABLE pacientes
add column medico_id bigint AFTER cpf,
ADD CONSTRAINT fk_pacientes_medico
    FOREIGN KEY (medico_id) REFERENCES medicos(id);