
DROP DATABASE IF EXISTS vetfouryou;
CREATE DATABASE IF NOT EXISTS vetfouryou;
USE vetfouryou;


-- Tabela para guardar os dados de login dos donos dos pets.
CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(100),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    isAdmin CHAR(1), -- 's' para sim, 'n' para não
    cpf VARCHAR(14) NOT NULL UNIQUE,
    isActive CHAR(1) -- 's' para ativo, 'n' para inativo
);

-- Tabela para guardar informações do plano/cartão do paciente.
CREATE TABLE Cartao (
    id_cartao INT PRIMARY KEY AUTO_INCREMENT,
    plano INT,
    validade DATE,
    titular VARCHAR(100)
);

-- Tabela para guardar os dados dos animais (pacientes).
CREATE TABLE Paciente (
    id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    raca VARCHAR(50),
    animal VARCHAR(50),
    idade INT,
    sexo CHAR(1), -- 'M' para macho, 'F' para fêmea
    id_usuario INT, -- Chave estrangeira para conectar o paciente ao seu dono (Usuario)
    plano INT,      -- Chave estrangeira para conectar o paciente ao seu cartão/plano
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (plano) REFERENCES Cartao(id_cartao)
);

-- Tabela para o endereço do dono do pet.
CREATE TABLE Endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100),
    numero INT,
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2),
    cep VARCHAR(10),
    id_usuario INT, -- Conecta o endereço ao usuário
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Tabela para os dados dos veterinários.
CREATE TABLE Veterinario (
    id_veterinario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    especialidade VARCHAR(100),
    crmvet INT NOT NULL UNIQUE
);

-- Tabela para registrar cada consulta.
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

-- Tabela para registrar os exames pedidos em uma consulta.
CREATE TABLE Exame (
    id_exame INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100),
    resultado VARCHAR(255),
    data DATE,
    id_consulta INT,
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

-- Tabela de produtos da clínica (remédios, ração, etc.).
CREATE TABLE Produto (
    id_produto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    tipo VARCHAR(50),
    preco DECIMAL(10,2),
    estoque INT
);

-- Tabela de prescrições de produtos/remédios feitas em uma consulta.
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

-- Tabela para controlar internações de pacientes.
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

-- Tabela para registrar cirurgias.
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

-- Tabela para registrar os pagamentos.
CREATE TABLE Pagamento (
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    servico VARCHAR(100),
    forma_pagamento VARCHAR(50),
    valor DECIMAL(10,2),
    status CHAR(1), -- 's' para pago, 'n' para não pago (devedor)
    data_pagamento DATE,
    id_paciente INT,
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente)
);

-- Insere 10 usuários (donos dos pets).
INSERT INTO Usuario (nome, telefone, email, username, password, isAdmin, cpf, isActive) VALUES
('Ana Silva', '111111111', 'p1@email.com', 'user1', 'pass1', 'n', '111.111.111-11', 's'),
('Bruno Costa', '222222222', 'p2@email.com', 'user2', 'pass2', 'n', '222.222.222-22', 's'),
('Carlos Dias', '333333333', 'p3@email.com', 'user3', 'pass3', 'n', '333.333.333-33', 's'),
('Daniela Souza', '444444444', 'p4@email.com', 'user4', 'pass4', 'n', '444.444.444-44', 's'),
('Eduardo Lima', '555555555', 'p5@email.com', 'user5', 'pass5', 'n', '555.555.555-55', 's'),
('Fernanda Alves', '666666666', 'p6@email.com', 'user6', 'pass6', 'n', '666.666.666-66', 's'),
('Gustavo Pereira', '777777777', 'p7@email.com', 'user7', 'pass7', 'n', '777.777.777-77', 's'),
('Helena Santos', '888888888', 'p8@email.com', 'user8', 'pass8', 'n', '888.888.888-88', 's'),
('Igor Martins', '999999999', 'p9@email.com', 'user9', 'pass9', 'n', '999.999.999-99', 's'),
('Julia Ferreira', '1010101010', 'p10@email.com', 'user10', 'pass10', 'n', '000.000.000-00', 's');

-- Insere 10 cartões/planos.
INSERT INTO Cartao (plano, validade, titular) VALUES
(1001, '2026-12-31', 'Ana Silva'), (1002, '2026-12-31', 'Bruno Costa'),
(1003, '2026-12-31', 'Carlos Dias'), (1004, '2026-12-31', 'Daniela Souza'),
(1005, '2026-12-31', 'Eduardo Lima'), (1006, '2026-12-31', 'Fernanda Alves'),
(1007, '2026-12-31', 'Gustavo Pereira'), (1008, '2026-12-31', 'Helena Santos'),
(1009, '2026-12-31', 'Igor Martins'), (1010, '2026-12-31', 'Julia Ferreira');

-- Insere 10 pacientes, associando cada um a um usuário e um cartão.
INSERT INTO Paciente (nome, raca, animal, idade, sexo, id_usuario, plano) VALUES
('Rex', 'Golden Retriever', 'Cachorro', 5, 'M', 1, 1), ('Mimi', 'Siamês', 'Gato', 3, 'F', 2, 2),
('Thor', 'Bulldog', 'Cachorro', 2, 'M', 3, 3), ('Luna', 'Poodle', 'Cachorro', 4, 'F', 4, 4),
('Simba', 'Persa', 'Gato', 6, 'M', 5, 5), ('Nina', 'Labrador', 'Cachorro', 1, 'F', 6, 6),
('Apolo', 'Vira-lata', 'Cachorro', 7, 'M', 7, 7), ('Mel', 'Angorá', 'Gato', 8, 'F', 8, 8),
('Zeus', 'Pastor Alemão', 'Cachorro', 3, 'M', 9, 9), ('Lola', 'Shih Tzu', 'Cachorro', 5, 'F', 10, 10);

-- Insere 5 registros de pagamento para simular devedores e adimplentes.
INSERT INTO Pagamento (servico, forma_pagamento, valor, status, data_pagamento, id_paciente) VALUES
('Mensalidade', 'Cartão', 150.00, 's', '2025-10-01', 1),
('Mensalidade', 'Pendente', 200.00, 'n', NULL, 3),
('Mensalidade', 'Cartão', 100.00, 's', '2025-09-25', 5),
('Mensalidade', 'Pendente', 250.00, 'n', NULL, 7),
('Mensalidade', 'Cartão', 300.00, 's', '2025-10-05', 9);

SELECT * FROM Pagamento;

SELECT id_paciente, nome FROM Paciente;
