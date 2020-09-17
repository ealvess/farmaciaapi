CREATE TABLE centro_de_custo(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	ativo BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO centro_de_custo (nome, ativo) values ('Clinica Médica', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Clinica Cirurgica', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Maternidade', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Pediatria', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Centro Cirurgico', true);
INSERT INTO centro_de_custo (nome, ativo) values ('UTI', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Pronto Socorro', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Higienização', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Nutrição', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Administrativo', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Hospital de Taquarussu', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Hospital Municipal de Ivinhema', true);
INSERT INTO centro_de_custo (nome, ativo) values ('Hospital ', true);
