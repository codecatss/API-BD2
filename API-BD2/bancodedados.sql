-- Criação do banco de dados

create database 2rp;

-- Configurando user e password padrão do banco de dados para conectar ao JDbC

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin123';

GRANT SELECT, INSERT, UPDATE ON 2rp.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;

-- Define o banco de dados 2rp como o padrão

use 2rp;

-- Criação de tabelas

create table
    usuario(
        username VARCHAR(20) NOT NULL,
        nome VARCHAR (80) NOT NULL,
        senha VARCHAR(15) NOT NULL,
        funcao ENUM(
            'admin',
            'gestor',
            'colaborador'
        ) NOT NULL,
        status_user ENUM('ativo', 'inativo') NOT NULL DEFAULT 'ativo',
        PRIMARY KEY(username)
    );

create table
    centro_resultado(
        nome VARCHAR(30) NOT NULL,
        codigo_cr VARCHAR(10) NOT NULL,
        sigla VARCHAR (10) NOT NULL,
        status_cr ENUM('ativo', 'inativo') NOT NULL DEFAULT 'ativo',
        PRIMARY KEY(codigo_cr),
        UNIQUE(sigla)
    );

create table
    cliente (
        cnpj BIGINT NOT NULL,
        razao_social VARCHAR(150) NOT NULL,
        status_cliente ENUM('ativo', 'inativo') NOT NULL DEFAULT 'ativo',
        PRIMARY KEY(cnpj)
    );

create table
    hora(
        id int AUTO_INCREMENT NOT NULL,
        cod_cr VARCHAR(30) NOT NULL,
        username_lancador VARCHAR(20) NOT NULL,
        cnpj_cliente BIGINT NOT NULL,
        data_hora_inicio DATETIME NOT NULL,
        data_hora_fim DATETIME NOT NULL,
        tipo VARCHAR(15) NOT NULL,
        justificativa_lancamento VARCHAR(500) NOT NULL,
        projeto VARCHAR(100) NOT NULL,
        username_aprovador VARCHAR(20),
        justificativa_negacao VARCHAR(500),
        status_aprovacao ENUM(
            'pendente',
            'aprovado_gestor',
            'aprovado_rh',
            'negado'
        ) NOT NULL DEFAULT 'pendente',
        solicitante_lancamento VARCHAR(30) NOT NULL,
        Foreign Key (username_lancador) REFERENCES usuario(username),
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr),
        Foreign Key (cnpj_cliente) REFERENCES cliente(cnpj),
        Foreign Key (username_aprovador) REFERENCES usuario (username),
        PRIMARY KEY (id)
    );

create table
    contrato(
        id int(10) AUTO_INCREMENT NOT NULL,
        cod_cr VARCHAR(10) NOT NULL,
        cnpj_cliente BIGINT NOT NULL,
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr),
        Foreign KEY (cnpj_cliente) REFERENCES cliente(cnpj),
        PRIMARY KEY(id)
    );

create table
    integrante(
        gestor BOOLEAN NOT NULL,
        username_integrante VARCHAR(20) NOT NULL,
        cod_cr VARCHAR(10) NOT NULL,
        Foreign Key (username_integrante) REFERENCES usuario(username),
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr),
        PRIMARY KEY (username_integrante, cod_cr)
    );

create table
    verba(
        cod_verba INT NOT NULL,
        nome_verba VARCHAR(50),
        fator_multiplicacao DOUBLE NOT NULL,
        hora_inicio TIME,
        hora_fim TIME,
        PRIMARY KEY (cod_verba)
    );

-- Inserção de dados nas tabelas

-- Adição de Usuários

INSERT INTO
    usuario (username, nome, senha, funcao)
VALUES (
        'admin',
        'Admin',
        'admin123',
        'admin'
    );

INSERT INTO
    usuario (username, nome, senha, funcao)
VALUES (
        'brendel',
        'Brendel Marques',
        'dev123',
        'colaborador'
    ), (
        'caio',
        'Caio Sousa',
        'dev123',
        'colaborador'
    ), (
        'larissa',
        'Larissa Fernanda',
        'dev123',
        'colaborador'
    ), (
        'laroy',
        'Laroy Prado',
        'dev123',
        'colaborador'
    ), (
        'markus',
        'Markus Gomes',
        'dev123',
        'colaborador'
    ), (
        'mikaela',
        'Mikaela Petronilho',
        'dev123',
        'colaborador'
    ), (
        'nicole',
        'Nicole Souza',
        'dev123',
        'gestor'
    ), (
        'willian',
        'Willian Danko',
        'dev123',
        'colaborador'
    );

-- Adição de centro de resultados

INSERT INTO
    centro_resultado (nome, codigo_cr, sigla)
VALUES ('Codecats', '13652', 'CCTS');

-- Adição de Clientes

INSERT INTO
    cliente (razao_social, cnpj)
VALUES ('Fatec', 123456789), ('2RP', 987654321);

-- Adição integrantes no centro de resultado

INSERT INTO
    integrante (
        gestor,
        username_integrante,
        cod_cr
    )
VALUES (true, "nicole", 13652), (false, "brendel", 13652), (false, "caio", 13652), (false, "larissa", 13652), (false, "laroy", 13652), (false, "markus", 13652), (false, "mikaela", 13652), (false, "willian", 13652);

-- Adição dados de contrato cnpj

INSERT INTO
    contrato (cod_cr, cnpj_cliente)
VALUES ("13652", 987654321);

-- Adição de horas à tabela

INSERT INTO
    hora (
        cod_cr,
        username_lancador,
        cnpj_cliente,
        data_hora_inicio,
        data_hora_fim,
        tipo,
        justificativa_lancamento,
        projeto,
        solicitante_lancamento
    )
VALUES (
        13652,
        "larissa",
        123456789,
        "2023-04-15 23:30:00",
        "2023-04-16 01:20:00",
        "EXTRA",
        "Populando os bancos",
        "Projeto Integrador",
        "Mineda"
    ), (
        13652,
        "laroy",
        123456789,
        "2023-04-15 23:30:00",
        "2023-04-16 01:20:00",
        "EXTRA",
        "Populando os bancos",
        "Projeto Integrador",
        "Mineda"
    ), (
        13652,
        "caio",
        123456789,
        "2023-04-15 21:30:00",
        "2023-04-16 23:20:00",
        "EXTRA",
        "Conexão de banco de dados JDBC",
        "Projeto Integrador",
        "Mineda"
    );

-- Aprovação de hora-extra na tabela hora

UPDATE hora
SET
    username_aprovador = 'nicole',
    status_aprovacao = 'aprovado'
WHERE id = 1;

UPDATE hora
SET
    username_aprovador = 'nicole',
    justificativa_negacao = 'não foi pedido',
    status_aprovacao = 'negado'
WHERE id = 3;