-- Configuração de usuário e senha padrão do banco de dados para conectar ao JDbC
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin123';

GRANT SELECT, INSERT, UPDATE ON 2rp.* TO 'admin'@'localhost';


FLUSH PRIVILEGES;

-- Criação do banco de dados
create database 2rp;

-- Define o banco de dados 2rp como o padrão
use 2rp;

-- Criação de tabelas
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
        cnpj BIGINT PRIMARY KEY NOT NULL
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
    contrato(
		id int(10) auto_increment primary key,
        cod_cr VARCHAR(10) not NULL,
        cnpj_cliente BIGINT NOT NULL,
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr),
        Foreign KEY (cnpj_cliente) REFERENCES cliente(cnpj)
    );

create table
    integrantes (
        gestor BOOLEAN NOT NULL,
        username_integrante VARCHAR(20) not null,
        cod_cr VARCHAR(10) not NULL,
        Foreign Key (username_integrante) REFERENCES usuarios(username),
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr),
        PRIMARY KEY (username_integrante, cod_cr)
    );

-- Inserção de dados na tabela
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

INSERT INTO
    usuarios(
        username,
        nome,
        senha,
        funcao,
        status
    )
VALUES (
        'brendel',
        'Brendel Marques',
        'dev123',
        'colaborador',
        'Ativo'
    ), (
        'caio',
        'Caio Sousa',
        'dev123',
        'colaborador',
        'Ativo'
    ), (
        'larissa',
        'Larissa Fernanda',
        'dev123',
        'colaborador',
        'Ativo'
    ), (
        'laroy',
        'Laroy Prado',
        'dev123',
        'colaborador',
        'Ativo'
    ), (
        'markus',
        'Markus Gomes',
        'dev123',
        'colaborador',
        'Ativo'
    ), (
        'mikaela',
        'Mikaela Petronilho',
        'dev123',
        'colaborador',
        'Ativo'
    ), (
        'nicole',
        'Nicole Souza',
        'dev123',
        'gestor',
        'Ativo'
    ), (
        'willian',
        'Willian Danko',
        'dev123',
        'colaborador',
        'Ativo'
    );

-- Adição de centro de resultados

INSERT INTO
    centro_resultado (
        nome,
        status_aprovacao,
        codigo_cr,
        sigla
    )
VALUES (
        'Codecats',
        'ativo',
        '13652',
        'CCTS'
    );

-- Seleção de dados na tabela
SELECT * FROM usuarios;
