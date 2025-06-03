CREATE table medicos(
id bigint primary key not null auto_increment,
nome varchar(100) not null,
idade int not null,
cpf varchar(100) not null,
salario decimal (7,2) not null,
ativo tinyint not null
);