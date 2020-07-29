CREATE TABLE medico(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	rg VARCHAR(11),
	crm VARCHAR(10) NOT NULL,
	data_inscricao DATE,
	email VARCHAR (100),
	telefone VARCHAR(20),
	celular VARCHAR(20) NOT NULL, 
	ativo BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Márcia Clarice Sophia Pinto', '24461917894', '290838964', '23789', '2018/04/29', 'marcia@gmail.com', '34456787', '67987654321', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Francisco Noah Freitas', '67307201151', '165415095', '4536/MS', '2014/07/19', 'francisco@gmail.com', '', '67987654321', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Tânia Josefa Marcela Jesus', '95441215798', '382129349', '12400/SP', '2016/07/19', 'tania@gmail.com', '', '67987654321', false);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Fátima Sophie Almada', '19546680079', '216142325', '1400/SP', '2017/07/07', 'fatima@gmail.com', '', '67987654321', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Hugo Davi Nogueira', '50428253717', '160617005', '39400/SP', '2017/07/07', 'hugo@gmail.com', '', '67987654321', false);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Thales Bryan João de Paula', '29039019703', '221778998', '39480/RJ', '2017/07/07', 'thales@gmail.com', '', '67987654321', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Martin Augusto Nunes', '63588414673', '258461135', '54737/MS', '2019/09/07', 'martin@gmail.com', '', '67987654321', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Sophia Isabelle da Mata', '93859205102', '489723196', '5497/MS', '2018/08/17', 'sophiaisabelledamata@com.br', '8635669939', '86992676174', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Mateus Ryan Sales', '59105967481', '190128896', '12347/MS', '2018/08/17', 'mateusryansales..mateusryansales@fluxioneventos.com.br', '9828666842', '98985347702', true);
INSERT INTO medico(nome, cpf, rg, crm, data_inscricao, email, telefone, celular, ativo) VALUES('Isis Isabella Aparecida Porto', '25839097624', '474731637', '12347/MS', '2018/08/17', 'iisisisabellaaparecidaporto@bitco.cc', '7939541088', '79996558473', true);
