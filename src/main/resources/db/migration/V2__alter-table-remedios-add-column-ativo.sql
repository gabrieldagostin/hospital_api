ALTER TABLE remedios ADD COLUMN ativo BOOLEAN;
UPDATE remedios SET ativo = TRUE;
