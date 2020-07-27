CREATE TABLE medico(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100),
	cpf VARCHAR(11),
	rg VARCHAR(11),
	crm VARCHAR(10),
	data_inscricao DATE,
	situacao VARCHAR(50),
	email VARCHAR (100),
	telefone VARCHAR(20),
	celular VARCHAR(20), 
	sexo VARCHAR(1)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Márcia Clarice Sophia Pinto', '24461917894', '290838964', '23789', '2018/04/29', 'REGULAR', 'marcia@gmail.com', '34456787', '67987654321', 'F');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Francisco Noah Freitas', '67307201151', '165415095', '4536/MS', '2014/07/19', 'REGULAR', 'francisco@gmail.com', '', '67987654321', 'M');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Tânia Josefa Marcela Jesus', '95441215798', '382129349', '12400/SP', '2016/07/19', 'REGULAR', 'tania@gmail.com', '', '67987654321', 'F');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Fátima Sophie Almada', '19546680079', '216142325', '1400/SP', '2017/07/07', 'REGULAR', 'fatima@gmail.com', '', '67987654321', 'F');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Hugo Davi Nogueira', '50428253717', '160617005', '39400/SP', '2017/07/07', 'REGULAR', 'hugo@gmail.com', '', '67987654321', 'M');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Thales Bryan João de Paula', '29039019703', '221778998', '39480/RJ', '2017/07/07', 'IRREGULAR', 'thales@gmail.com', '', '67987654321', 'M');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Martin Augusto Nunes', '63588414673', '258461135', '54737/MS', '2019/09/07', 'REGULAR', 'martin@gmail.com', '', '67987654321', 'M');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Sophia Isabelle da Mata', '93859205102', '489723196', '5497/MS', '2018/08/17', 'REGULAR', 'sophiaisabelledamata@com.br', '8635669939', '86992676174', 'F');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Mateus Ryan Sales', '59105967481', '190128896', '12347/MS', '2018/08/17', 'REGULAR', 'mateusryansales..mateusryansales@fluxioneventos.com.br', '9828666842', '98985347702', 'M');
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, situacao, email, telefone, celular, sexo) VALUES('Isis Isabella Aparecida Porto', '25839097624', '474731637', '12347/MS', '2018/08/17', 'REGULAR', 'iisisisabellaaparecidaporto@bitco.cc', '7939541088', '79996558473', 'F');
