CREATE TABLE fornecedor( 
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
	nome_fantasia VARCHAR(100) NOT NULL,
	razao_social VARCHAR(50) NOT NULL,
	cnpj VARCHAR(100) NOT NULL,
	inscricao_estadual VARCHAR(50) NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero INTEGER NOT NULL,
	bairro VARCHAR(100) NOT NULL,
	cep VARCHAR(50) NOT NULL,
	cidade VARCHAR(100) NOT NULL, 
	estado VARCHAR(2) NOT NULL,
	telefone VARCHAR(100) NOT NULL,
	telefone2 VARCHAR(100),
	telefone3 VARCHAR(100),
	telefone_fax VARCHAR(100), 
	email VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Marketing ME', 'Marlene e José Marketing ME', '91792404000110', '037199938970', 'Rua Paschoal Moreira', '201', 'Vila Ida', '01245820', 'Franco da Rocha', 'SP', '1128929733', 'contabil@marleneejosemarketingme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Telecom Ltda', 'Giovana e Vera Telecom Ltda', '69078400000116', '058951197826', 'Travessa Norolar', '182', 'Planalto', '2514565', 'Araçatuba', 'SP', '1826541239', 'financeiro@giovanaeveratelecomltda.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Contábil ME', 'Francisco e Regina Contábil ME', '57582432000175', '840482129176', 'Rua Itália', '466', 'Jardim Primeiro de Maio', '5245465', 'Carapicuíba', 'SP', '1128739291', 'juridico@franciscoereginacontabilme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Telecom ME', 'Gael e Cauã Telecom ME', '25230468000101', '782957922816', 'Rua Danilo Mic Alli', '450', 'Loteamento Municipal São Carlos 3', '154587', 'São Carlos', 'SP', '1636823448', 'fiscal@gaelecauatelecomme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES(' Informática ME', 'Rebeca e Hadassa Informática ME', '48426523000120', '845650395341', 'Travessa Santa Rita', '842', 'Campo Belo', '25498796', 'São Paulo', 'SP', '1138837674', 'producao@rebecaehadassainformaticame.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES(' Transportes Ltda', 'Levi e Luan Transportes Ltda', '18949522000108', '280891555', 'Rua Baraúnas', '254', 'Parque dos Novos Estados', '14546', 'Campo Grande', 'MS', '6728827402', 'cobranca@levieluantransportesltda.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Telecomunicações ME', 'Eduardo e Bento Telecomunicações ME', '66775644000160', '289243530', 'Travessa Nerópolis', '405', 'Parque Atlântico', '154488', 'Campo Grande', 'MS', '6737986619', 'pesquisa@eduardoebentotelecomunicacoesme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('ETransportes ME', 'Calebe e Tânia Transportes ME', '82380560000183', '282935355', 'Rua João Lopes', '516', 'Vila Santa Catarina', '154488', 'Dourados', 'MS', '6736290847', 'contato@calebeetaniatransportesme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Telemed Ltda', 'Raquel e Arthur Telemed Ltda', '22661109000102', '284316385', 'Rua Quatorze de Julho', '828', 'Centro', '64658', 'Campo Grande', 'MS', '6729590969', 'contabilidade@raquelearthurtelecomltda.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Transportes Med ME', 'Cláudia e Benício Transportes Med ME', '14935461000160', '280791577', 'Rua Duque de Caxias, s/n', '145', 'Centro', '45418694', 'Pirapora', 'MS', '6738889158', 'financeiro@claudiaebeniciotransportesme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Medical Ltda', 'Cláudio e Andreia Medical Ltda', '61395523000179', '281696772', 'Rua Principal 1', '468', 'Núcleo Industrial', '454865', 'Campo Grande', 'MS', '6729135345', 'administracao@claudioeandreiamudancasltda.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Confarma ME', 'Anthony e Renata Confarma ME', '19171506000191', '0989592404856', 'Rua Frederico Rossini', '213', 'Menezes', '36774118', 'Cataguases', 'MG', '3235758740', 'desenvolvimento@anthonyerenatacontabilme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Teles ME', 'Clarice e Sophia Teles ME', '69504137000180', '4155885480491', 'Rua Evânio Labre', '556', 'Parque Alto da Figueira', '37004770', 'Varginha', 'MG', '3537205495', 'pesquisa@clariceesophiatelesme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Transportes Medicos ME', 'Yago e Sabrina Transportes Medicos ME', '78442130000171', '9661842351928', 'Rua Vicentina Coutinho Camargos', '984', 'Álvaro Camargos', '30860130', 'Belo Horizonte', 'MG', '3125683669', 'rh@yagoesabrinatransportesme.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Limpeza ME', 'Bernardo e Fernando Limpeza ME', '21057047000161', '8406979807324', 'Avenida Barão Homem de Melo', '726', 'Estoril', '30494270', 'Belo Horizonte', 'MG', '3138350455', 'rh@bernardoefernandolimpezame.com.br');
INSERT INTO fornecedor(nome_fantasia, razao_social, cnpj, inscricao_estadual, logradouro, numero, bairro, cep, cidade, estado, telefone, email) VALUES('Comercial ME', 'Brenda e Rafael Comercial ME', '05159887000189', '0078259504731', 'Rua Petrônio Mendes de Souza', '380', 'Grão Pará', '39800168', 'Teófilo Otoni', 'MG', '3328220079', 'suporte@brendaerafaelcomerciodebebidasme.com.br');
