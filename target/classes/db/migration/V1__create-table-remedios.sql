CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE remedios (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    via VARCHAR(100) NOT NULL,
    lote VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    validade DATE NOT NULL,
    laboratorio VARCHAR(100) NOT NULL
);
