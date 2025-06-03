CREATE TABLE pacientes(
id bigint primary key not null auto_increment,
nome varchar(100) not null,
idade int not null,
cpf varchar(100) not null,
ativo int not null
);