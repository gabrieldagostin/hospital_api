CREATE TABLE pacientes (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    cpf VARCHAR(100) NOT NULL UNIQUE,
    ativo BOOLEAN NOT NULL
);
