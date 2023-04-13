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