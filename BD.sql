CREATE DATABASE IF NOT EXISTS controle_gastos;
USE controle_gastos;

DROP TABLE IF EXISTS log_acoes;
DROP TABLE IF EXISTS metas;
DROP TABLE IF EXISTS gasto;
DROP TABLE IF EXISTS receita;
DROP TABLE IF EXISTS tipo_gasto;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE categoria (
    idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

INSERT INTO categoria (nome) VALUES
('Alimentação'),
('Transporte'),
('Lazer'),
('Educação'),
('Saúde'),
('Serviços'),
('Moradia');

CREATE TABLE tipo_gasto (
    idTipo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO tipo_gasto (nome) VALUES
('Fixo'),
('Variável'),
('Recorrente');

CREATE TABLE receita (
    idReceita INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    dataRecebida DATE NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

CREATE TABLE gasto (
    idGasto INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

CREATE TABLE metas (
    idMeta INT AUTO_INCREMENT PRIMARY KEY,
    valorLimite DECIMAL(10,2) NOT NULL,
    mes INT NOT NULL,
    ano INT NOT NULL,
    categoriaID INT NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (categoriaID) REFERENCES categoria(idCategoria),
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

CREATE TABLE log_acoes (
    idLog INT AUTO_INCREMENT PRIMARY KEY,
    acao VARCHAR(255) NOT NULL,
    dataHora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuarioID INT,
    FOREIGN KEY (usuarioID) REFERENCES usuario(idUsuario)
);

INSERT INTO usuario (nome) VALUES
('João'),
('Maria'),
('Pedro'),
('Ana'),
('Lucas');

INSERT INTO gasto (descricao, valor, categoria, idUsuario) VALUES
('Supermercado', 150.75, 'Alimentação', 1),
('Gasolina', 90.00, 'Transporte', 1),
('Cinema', 30.00, 'Lazer', 1),
('Remédio', 45.20, 'Saúde', 2),
('Curso de inglês', 250.00, 'Educação', 3),
('Academia', 120.00, 'Saúde', 4),
('Uber', 22.50, 'Transporte', 5),
('Hambúrguer', 35.00, 'Alimentação', 5),
('Internet', 120.00, 'Serviços', 1),
('Aluguel', 1200.00, 'Moradia', 1);

INSERT INTO receita (descricao, valor, dataRecebida, idUsuario) VALUES
('Salário', 3000.00, '2025-11-01', 1),
('Freelance', 450.00, '2025-11-07', 1),
('Bolsa de estudos', 600.00, '2025-11-03', 3);

INSERT INTO metas (valorLimite, mes, ano, categoriaID, idUsuario) VALUES
(500.00, 11, 2025, 1, 1),
(200.00, 11, 2025, 2, 1),
(300.00, 11, 2025, 3, 1);

INSERT INTO log_acoes (acao, usuarioID) VALUES
('Usuário João adicionou gasto', 1),
('Usuário Maria adicionou gasto', 2);

SELECT * FROM usuario;
SELECT * FROM gasto;
SELECT * FROM receita;
SELECT * FROM metas;
SELECT * FROM log_acoes;

SELECT 
    u.nome,
    g.descricao,
    g.valor,
    g.categoria
FROM gasto g
JOIN usuario u ON u.idUsuario = g.idUsuario
ORDER BY u.nome;

SELECT 
    categoria,
    SUM(valor) AS total_categoria
FROM gasto
GROUP BY categoria;

SELECT 
    u.nome,
    SUM(g.valor) AS total_gastos
FROM usuario u
LEFT JOIN gasto g ON u.idUsuario = g.idUsuario
GROUP BY u.idUsuario;