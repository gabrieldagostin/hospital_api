CREATE TABLE medicos (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    cpf VARCHAR(100) NOT NULL,
    salario NUMERIC(7,2) NOT NULL,
    ativo BOOLEAN NOT NULL
);
