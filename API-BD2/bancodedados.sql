-- Configurando user e password padrão do banco de dados para conectar ao JDbC

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin123';

GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;

create database 2rp;

use 2rp;

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
        data_hora_inicio DATETIME not NULL,
        data_hora_fim DATETIME not NULL,
        tipo VARCHAR(15) NOT NULL,
        username_lancador VARCHAR(20) not null,
        cod_cr VARCHAR(30) NOT NULL,
        justificativa VARCHAR(500),
        projeto VARCHAR(100),
        Foreign Key (username_lancador) REFERENCES usuarios(username),
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr)
    );

create table
    centro_resultado(
        nome VARCHAR(30) NOT NULL,
        status_aprovacao ENUM('ativo', 'inativo') NOT NULL,
        codigo_cr VARCHAR(10) not NULL PRIMARY KEY,
        sigla VARCHAR (10) NOT NULL UNIQUE
    );

create table
    cliente (
        razao_social VARCHAR(70) NOT NULL,
        status_clientes ENUM('ativo', 'inativo') NOT NULL,
        cpnj BIGINT PRIMARY KEY NOT NULL
    );

INSERT INTO
    usuarios(
        username,
        nome,
        senha,
        funcao,
        status
    )
VALUES (
        'admin',
        'Admin',
        'admin123',
        'admin',
        'Ativo'
    );

SELECT * FROM usuarios;
