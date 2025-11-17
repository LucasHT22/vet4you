CREATE DATABASE IF NOT EXISTS vetfouryou;
USE vetfouryou;
drop database vetfouryou;

-- Tabela Usuario
CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(100),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    isAdmin CHAR(1),
    cpf VARCHAR(14) NOT NULL,
    isActive CHAR(1),
    UNIQUE (username, cpf)
);

-- Tabela Cartao (antes de ser referenciada por Paciente)
CREATE TABLE Cartao (
    id_cartao INT PRIMARY KEY AUTO_INCREMENT,
    plano INT,
    validade DATE,
    titular VARCHAR(100)
);

-- Tabela Paciente
CREATE TABLE Paciente (
    id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    raca VARCHAR(50),
    animal VARCHAR(50),
    idade INT,
    sexo CHAR(1),
    id_usuario INT,
    plano INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (plano) REFERENCES Cartao(id_cartao)
);

-- Tabela Endereco
CREATE TABLE Endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100),
    numero INT,
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2),
    cep VARCHAR(10),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Tabela Veterinario
CREATE TABLE Veterinario (
    id_veterinario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    especialidade VARCHAR(100),
    crmvet INT NOT NULL UNIQUE
);

-- Tabela Consulta
CREATE TABLE Consulta (
    id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    timestamp DATETIME,
    motivo VARCHAR(255),
    diagnostico VARCHAR(255),
    id_paciente INT,
    id_veterinario INT,
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente),
    FOREIGN KEY (id_veterinario) REFERENCES Veterinario(id_veterinario)
);

-- Tabela Exame
CREATE TABLE Exame (
    id_exame INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100),
    resultado VARCHAR(255),
    data DATE,
    id_consulta INT,
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

-- Tabela Produto
CREATE TABLE Produto (
    id_produto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    tipo VARCHAR(50),
    preco DECIMAL(10,2),
    estoque INT
);

-- Tabela Prescricao
CREATE TABLE Prescricao (
    id_prescricao INT PRIMARY KEY AUTO_INCREMENT,
    quantidade INT,
    instrucoes VARCHAR(255),
    id_consulta INT,
    id_veterinario INT,
    id_produto INT,
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta),
    FOREIGN KEY (id_veterinario) REFERENCES Veterinario(id_veterinario),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

-- Tabela Internacao
CREATE TABLE Internacao (
    id_internacao INT PRIMARY KEY AUTO_INCREMENT,
    data_entrada DATE,
    data_saida DATE,
    observacoes TEXT,
    id_veterinario INT,
    id_paciente INT,
    FOREIGN KEY (id_veterinario) REFERENCES Veterinario(id_veterinario),
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente)
);

-- Tabela Cirurgia
CREATE TABLE Cirurgia (
    id_cirurgia INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100),
    data DATE,
    observacoes TEXT,
    id_paciente INT,
    id_veterinario INT,
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente),
    FOREIGN KEY (id_veterinario) REFERENCES Veterinario(id_veterinario)
);

-- Tabela Pagamento
CREATE TABLE Pagamento (
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    servico VARCHAR(100),
    forma_pagamento VARCHAR(50),
    valor DECIMAL(10,2),
    status CHAR(1),
    data_pagamento DATE,
    id_paciente INT,
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente)
);

-- Criar 10 pacientes 
INSERT INTO Usuario (nome, telefone, email, username, password, isAdmin, cpf, isActive)
VALUES
('Paciente 1', '111111111', 'p1@email.com', 'user1', 'pass1', 'n', '000000001', 's'),
('Paciente 2', '222222222', 'p2@email.com', 'user2', 'pass2', 'n', '000000002', 's'),
('Paciente 3', '333333333', 'p3@email.com', 'user3', 'pass3', 'n', '000000003', 's'),
('Paciente 4', '444444444', 'p4@email.com', 'user4', 'pass4', 'n', '000000004', 's'),
('Paciente 5', '555555555', 'p5@email.com', 'user5', 'pass5', 'n', '000000005', 's'),
('Paciente 6', '666666666', 'p6@email.com', 'user6', 'pass6', 'n', '000000006', 's'),
('Paciente 7', '777777777', 'p7@email.com', 'user7', 'pass7', 'n', '000000007', 's'),
('Paciente 8', '888888888', 'p8@email.com', 'user8', 'pass8', 'n', '000000008', 's'),
('Paciente 9', '999999999', 'p9@email.com', 'user9', 'pass9', 'n', '000000009', 's'),
('Paciente 10', '1010101010', 'p10@email.com', 'user10', 'pass10', 'n', '000000010', 's');


INSERT INTO Cartao (plano, validade, titular) VALUES
(1001, '2026-12-31', 'Paciente 1'),
(1002, '2026-12-31', 'Paciente 2'),
(1003, '2026-12-31', 'Paciente 3'),
(1004, '2026-12-31', 'Paciente 4'),
(1005, '2026-12-31', 'Paciente 5'),
(1006, '2026-12-31', 'Paciente 6'),
(1007, '2026-12-31', 'Paciente 7'),
(1008, '2026-12-31', 'Paciente 8'),
(1009, '2026-12-31', 'Paciente 9'),
(1010, '2026-12-31', 'Paciente 10');

INSERT INTO Paciente (nome, raca, animal, idade, sexo, id_usuario, plano) VALUES
('Paciente 1', 'Raça1', 'Animal1', 5, 'M', 1, 1),
('Paciente 2', 'Raça2', 'Animal2', 3, 'F', 2, 2),
('Paciente 3', 'Raça3', 'Animal3', 2, 'M', 3, 3),
('Paciente 4', 'Raça4', 'Animal4', 4, 'F', 4, 4),
('Paciente 5', 'Raça5', 'Animal5', 6, 'M', 5, 5),
('Paciente 6', 'Raça6', 'Animal6', 1, 'F', 6, 6),
('Paciente 7', 'Raça7', 'Animal7', 7, 'M', 7, 7),
('Paciente 8', 'Raça8', 'Animal8', 8, 'F', 8, 8),
('Paciente 9', 'Raça9', 'Animal9', 3, 'M', 9, 9),
('Paciente 10', 'Raça10', 'Animal10', 5, 'F', 10, 10);

-- Inserir pagamentos (dividendos) para 5 pacientes diferentes (com valores diferentes)
INSERT INTO Pagamento (servico, forma_pagamento, valor, status, data_pagamento, id_paciente) VALUES
('Dividendo', 'Cartão', 15.00, 's', '2025-10-01', 1),
('Dividendo', 'Dinheiro', 20.00, 'n', NULL, 3),
('Dividendo', 'Cartão', 10.00, 's', '2025-09-25', 5),
('Dividendo', 'Dinheiro', 25.00, 'n', NULL, 7),
('Dividendo', 'Cartão', 30.00, 's', '2025-10-05', 9);

UPDATE Pagamento SET status = 's' WHERE id_paciente = 7;

select * from Pagamento;
SELECT id_paciente, nome FROM Paciente;
