CREATE TABLE Usuario 
( 
 id_usuario INT PRIMARY KEY AUTO_INCREMENT,  
 nome VARCHAR(n),  
 telefone VARCHAR(n),  
 email VARCHAR(n),  
 username VARCHAR(n) NOT NULL,  
 password VARCHAR(n) NOT NULL,  
 isAdmin CHAR(n),  
 cpf FLOAT NOT NULL,  
 isActive CHAR(n),  
 UNIQUE (username,cpf)
); 

CREATE TABLE Endereco 
( 
 id_endereco INT PRIMARY KEY AUTO_INCREMENT,  
 logradouro VARCHAR(n),  
 numero INT,  
 complemento VARCHAR(n),  
 bairro VARCHAR(n),  
 cidade VARCHAR(n),  
 estado CHAR(n),  
 cep INT,  
 id_usuario INT,  
); 

CREATE TABLE Paciente 
( 
 id_paciente INT PRIMARY KEY AUTO_INCREMENT,  
 nome VARCHAR(n),  
 raca VARCHAR(n),  
 animal VARCHAR(n),  
 idade INT,  
 sexo CHAR(n),  
 id_usuario INT,  
 plano INT,  
); 

CREATE TABLE Veterinario 
( 
 id_veterinario INT PRIMARY KEY AUTO_INCREMENT,  
 nome VARCHAR(n),  
 especialidade VARCHAR(n),  
 crmvet INT NOT NULL,  
 UNIQUE (crmvet)
); 

CREATE TABLE Consulta 
( 
 id_consulta INT PRIMARY KEY AUTO_INCREMENT,  
 timestamp DATE,  
 motivo VARCHAR(n),  
 diagnostico VARCHAR(n),  
 id_paciente INT,  
 id_veterinario INT,  
); 

CREATE TABLE Exame 
( 
 id_exame INT PRIMARY KEY AUTO_INCREMENT,  
 tipo VARCHAR(n),  
 resultado VARCHAR(n),  
 data DATE,  
 id_consulta INT,  
); 

CREATE TABLE Produto 
( 
 id_produto INT PRIMARY KEY,  
 nome INT,  
 tipo INT,  
 preco INT,  
 estoque INT,  
 id_consulta INT,  
); 

CREATE TABLE Prescrição 
( 
 id_prescricao INT PRIMARY KEY AUTO_INCREMENT,  
 quantidade INT,  
 instrucoes VARCHAR(n),  
 id_consulta INT,  
 id_veterinario INT,  
 id_produto INT,  
); 

CREATE TABLE Internação 
( 
 id_internacao INT PRIMARY KEY AUTO_INCREMENT,  
 data_entrada INT,  
 data_saida INT,  
 observacoes INT,  
 id_veterinario INT,  
 id_paciente INT,  
); 

CREATE TABLE Cirurgia 
( 
 id_cirurgia INT PRIMARY KEY AUTO_INCREMENT,  
 tipo VARCHAR(n),  
 data DATE,  
 observacoes INT,  
 id_paciente INT,  
 id_veterinario INT,  
); 

CREATE TABLE Pagamento 
( 
 id_pagamento INT PRIMARY KEY AUTO_INCREMENT,  
 servico VARCHAR(n),  
 forma_pagamento VARCHAR(n),  
 valor FLOAT,  
 status CHAR(n),  
 data_pagamento INT,  
 id_paciente INT,  
); 

CREATE TABLE Cartao 
( 
 id_cartao INT PRIMARY KEY AUTO_INCREMENT,  
 plano INT,  
 validade DATE,  
 titular VARCHAR(n),  
); 

ALTER TABLE Endereco ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
ALTER TABLE Paciente ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
ALTER TABLE Paciente ADD FOREIGN KEY(plano) REFERENCES Cartao (plano)
ALTER TABLE Consulta ADD FOREIGN KEY(id_paciente) REFERENCES Paciente (id_paciente)
ALTER TABLE Consulta ADD FOREIGN KEY(id_veterinario) REFERENCES Veterinario (id_veterinario)
ALTER TABLE Exame ADD FOREIGN KEY(id_consulta) REFERENCES Consulta (id_consulta)
ALTER TABLE Produto ADD FOREIGN KEY(id_consulta) REFERENCES Prescrição (id_consulta)
ALTER TABLE Prescrição ADD FOREIGN KEY(id_consulta) REFERENCES Consulta (id_consulta)
ALTER TABLE Prescrição ADD FOREIGN KEY(id_veterinario) REFERENCES Veterinario (id_veterinario)
ALTER TABLE Prescrição ADD FOREIGN KEY(id_produto) REFERENCES Produto (id_produto)
ALTER TABLE Internação ADD FOREIGN KEY(id_veterinario) REFERENCES Veterinario (id_veterinario)
ALTER TABLE Internação ADD FOREIGN KEY(id_paciente) REFERENCES Paciente (id_paciente)
ALTER TABLE Cirurgia ADD FOREIGN KEY(id_paciente) REFERENCES Paciente (id_paciente)
ALTER TABLE Cirurgia ADD FOREIGN KEY(id_veterinario) REFERENCES Veterinario (id_veterinario)
ALTER TABLE Pagamento ADD FOREIGN KEY(id_paciente) REFERENCES Paciente (id_paciente)
