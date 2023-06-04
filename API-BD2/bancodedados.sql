-- Criação do banco de dados

create database 2rp;

-- Configurando user e password padrão do banco de dados para conectar ao JDbC

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin123';

GRANT SELECT, INSERT, UPDATE, DELETE ON 2rp.* TO 'admin'@'localhost';

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
        aprovador_gestor VARCHAR(20),
        justificativa_negacao VARCHAR(500),
        status_aprovacao ENUM(
            'pendente',
            'aprovado_gestor',
            'aprovado_adm',
            'negado_gestor',
            'negado_adm'
        ) NOT NULL DEFAULT 'pendente',
        solicitante_lancamento VARCHAR(30) NOT NULL,
        feriado BOOLEAN DEFAULT FALSE,
        aprovador_ADM VARCHAR(20),
        Foreign Key (aprovador_ADM) REFERENCES usuario(username),
        Foreign Key (username_lancador) REFERENCES usuario(username),
        Foreign Key (cod_cr) REFERENCES centro_resultado(codigo_cr),
        Foreign Key (cnpj_cliente) REFERENCES cliente(cnpj),
        Foreign Key (aprovador_gestor) REFERENCES usuario (username),
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
    usuario (
        username,
        nome,
        senha,
        funcao,
        id
    )
VALUES (
        id int AUTO_INCREMENT NOT NULL 'admin',
        'Admin',
        'admin123',
        'admin' '0'
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
        'caio',
        'Caio Sousa',
        'dev123',
        'colaborador'
    );

INSERT INTO
    usuario (username, nome, senha, funcao)
VALUES (
        'ana',
        'Ana',
        987654321,
        'colaborador'
    ), (
        'maria',
        'Maria',
        123456789,
        'colaborador'
    ), (
        'pedro',
        'Pedro',
        987654321,
        'colaborador'
    ), (
        'joão',
        'João',
        123456789,
        'colaborador'
    ), (
        'gustavo',
        'Gustavo',
        987654321,
        'colaborador'
    ), (
        'fernanda',
        'Fernanda',
        123456789,
        'colaborador'
    ), (
        'carlos',
        'Carlos',
        987654321,
        'colaborador'
    ), (
        'rafael',
        'Rafael',
        123456789,
        'colaborador'
    ), (
        'lucas',
        'Lucas',
        987654321,
        'colaborador'
    ), (
        'mariana',
        'Mariana',
        123456789,
        'colaborador'
    );

INSERT INTO
    usuario (username, nome, senha, funcao)
VALUES (
        'ana',
        'Ana',
        987654321,
        'Desenvolvedora'
    ), (
        'maria',
        'Maria',
        123456789,
        'Desenvolvedora'
    ), (
        'pedro',
        'Pedro',
        987654321,
        'Desenvolvedor'
    ), (
        'joão',
        'João',
        123456789,
        'Analista de Sistemas'
    ), (
        'gustavo',
        'Gustavo',
        987654321,
        'Desenvolvedor'
    ), (
        'fernanda',
        'Fernanda',
        123456789,
        'Analista de Sistemas'
    ), (
        'carlos',
        'Carlos',
        987654321,
        'Desenvolvedor'
    ), (
        'rafael',
        'Rafael',
        123456789,
        'Desenvolvedor'
    ), (
        'lucas',
        'Lucas',
        987654321,
        'Desenvolvedor'
    ), (
        'mariana',
        'Mariana',
        123456789,
        'Analista de Sistemas'
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
    ), (
        13652,
        'ana',
        987654321,
        '2023-04-16 09:00:00',
        '2023-04-16 12:00:00',
        'NORMAL',
        'Reunião de equipe',
        'Projeto X',
        'Carlos'
    ), (
        13652,
        'maria',
        123456789,
        '2023-04-16 14:00:00',
        '2023-04-16 16:30:00',
        'NORMAL',
        'Desenvolvimento de funcionalidade',
        'Projeto Y',
        'Lucas'
    ), (
        13652,
        'pedro',
        987654321,
        '2023-04-17 10:30:00',
        '2023-04-17 12:30:00',
        'NORMAL',
        'Testes de integração',
        'Projeto Z',
        'Fernanda'
    ), (
        13652,
        'joão',
        123456789,
        '2023-04-18 08:00:00',
        '2023-04-18 10:00:00',
        'NORMAL',
        'Análise de requisitos',
        'Projeto X',
        'Rafaela'
    ), (
        13652,
        'gustavo',
        987654321,
        '2023-04-19 13:30:00',
        '2023-04-19 16:00:00',
        'NORMAL',
        'Revisão de código',
        'Projeto Y',
        'Mariana'
    ), (
        13652,
        'fernanda',
        123456789,
        '2023-04-20 09:30:00',
        '2023-04-20 11:00:00',
        'NORMAL',
        'Planejamento de sprint',
        'Projeto Z',
        'Gustavo'
    ), (
        13652,
        'carlos',
        987654321,
        '2023-04-21 08:30:00',
        '2023-04-21 10:30:00',
        'NORMAL',
        'Reunião com o cliente',
        'Projeto X',
        'João'
    ), (
        13652,
        'rafael',
        123456789,
        '2023-04-22 14:00:00',
        '2023-04-22 16:30:00',
        'NORMAL',
        'Desenvolvimento de funcionalidade',
        'Projeto Y',
        'Maria'
    ), (
        13652,
        'lucas',
        987654321,
        '2023-04-23 10:00:00',
        '2023-04-23 12:30:00',
        'NORMAL',
        'Testes de integração',
        'Projeto Z',
        'Fernanda'
    ), (
        13652,
        'mariana',
        123456789,
        '2023-04-24 08:30:00',
        '2023-04-24 10:30:00',
        'NORMAL',
        'Análise de requisitos',
        'Projeto X',
        'Rafaela'
    );

Certifique - se de ajustar as informações conforme necessário.User oq significa isso ? Search results Free1Cost: 34 ms Cannot
add or
update
    a child row: a foreign key constraint fails (
        `2rp`.`hora`,
        CONSTRAINT `hora_ibfk_2` FOREIGN KEY (`username_lancador`) REFERENCES `usuario` (`username`)
    ) ChatGPT Desculpe pelo erro.O erro que você está vendo ocorre porque o valor fornecido para o campo "username_lancador" na tabela "hora" não corresponde a um valor existente na tabela "usuario".Parece haver uma restrição de chave estrangeira entre essas duas tabelas.Certifique - se de que o valor fornecido para "username_lancador" corresponda a um valor válido existente na tabela "usuario".Verifique se os dados estão corretos e tente novamente.Se você estiver inserindo um novo registro na tabela "hora",
    verifique se o valor fornecido para "username_lancador" já existe na tabela "usuario" antes de realizar a inserção.Se o problema persistir,
    revise as restrições de chave estrangeira definidas nas tabelas relevantes para garantir que estejam corretas.Regenerate response
INSERT INTO
    verba (
        cod_verba,
        nome_verba,
        fator_multiplicacao,
        hora_inicio,
        hora_fim
    )
VALUES (
        1602,
        'HE_100',
        1.0,
        '06:00:00',
        '22:00:00'
    ), (
        1601,
        'HE_75',
        1.0,
        '06:00:00',
        '22:00:00'
    ), (
        3000,
        'HEN_75',
        1.1429,
        '22:00:00',
        '06:00:00'
    ), (
        3001,
        'HEN_100',
        1.1429,
        '22:00:00',
        '06:00:00'
    ), (
        1809,
        'AD_NOTURNO_30',
        1.1429,
        '22:00:00',
        '06:00:00'
    ), (
        3016,
        'SOBREAVISO',
        1.0,
        NULL,
        NULL
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