CREATE TABLE paciente(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(70) NOT NULL,
	cpf VARCHAR(11),
	rg VARCHAR(11),
	data_nascimento DATE NOT NULL,
	sexo VARCHAR(1),
	cartao_sus VARCHAR(15) NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Thales Luan Mácio Viana', '061548567', '365622746', '1981/06/19', 'F', '989987654321562', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Joana Andrea Cvalcanti', '065658567', '365622746', '1948/04/09', 'M', '989987654321562', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Pedro Santos', '065658567', '365622746', '1998/08/09', 'M', '989987654321562', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Ricardo Pereira', '065658567', '365622746', '1998/08/09', 'M', '989987654321562', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Josué Mariano', '065658567', '365622746', '1995/08/09', 'M', '989987654321562', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Pedro Barbosa', '065658567', '365622746', '1995/08/09', 'M', '989987654321562', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Henrique Medeiros', '065658567', '365622746', '1975/08/09', 'M', '989987654321562', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Carlos Santana', '065658567', '365622746', '1975/08/09', 'M', '989987654321562', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Leonardo Oliveira', '065658567', '365622746', '1975/08/09', 'M', '989987654321562', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG');
INSERT INTO paciente(nome, cpf, rg, data_nascimento, sexo, cartao_sus, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES('Isabela Martins', '065658567', '365622746', '1975/08/09', 'F', '989987654321562', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM');
