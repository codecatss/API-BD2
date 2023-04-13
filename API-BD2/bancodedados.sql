-- Active: 1679361494775@@127.0.0.1@3306

create database 2rp;

create table
    usuarios(
        username VARCHAR(20) not null PRIMARY KEY,
        nome VARCHAR (80) not null,
        senha VARCHAR(15) not NULL,
        funcao ENUM(
            'admin',
            'gestor',
            'colaborador'
        ) NOT NULL,
        status VARCHAR(10) not NULL
    );

create table
    hora(
        id int not null AUTO_INCREMENT PRIMARY KEY,
        username_lancador VARCHAR(20) not null,
        data_hora_inicio DATETIME not NULL,
        data_hora_fim DATETIME not NULL,
        tipo VARCHAR(15) NOT NULL,
        Foreign Key (username_lancador) REFERENCES usuarios(username)
    );

create table
    centro_de_resultado(
        nome VARCHAR(30) NOT NULL PRIMARY KEY,
        status_aprovacao ENUM('ativo', 'inativo') NOT NULL
    );
    
create table
    aprovacao (
        username_aprovador VARCHAR(20) not null NULL PRIMARY KEY,
        id_hora int PRIMARY KEY,
        status_aprovacao ENUM(
            'pendente',
            'aprovado',
            'reprovado'
        ) NOT NULL,
        justificativa Foreign Key (username_aprovador) REFERENCES usuarios(username)
    );
