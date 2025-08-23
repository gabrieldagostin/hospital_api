ALTER TABLE medicos
DROP COLUMN idade;

ALTER TABLE medicos
ADD COLUMN data_nasc DATE
AFTER sexo;