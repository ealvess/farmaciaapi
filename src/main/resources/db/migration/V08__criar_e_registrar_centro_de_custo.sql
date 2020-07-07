CREATE TABLE centro_de_custo(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO centro_de_custo (nome) values ('Clinica Médica');
INSERT INTO centro_de_custo (nome) values ('Clinica Cirurgica');
INSERT INTO centro_de_custo (nome) values ('Maternidade');
INSERT INTO centro_de_custo (nome) values ('Pediatria');
INSERT INTO centro_de_custo (nome) values ('Centro Cirurgico');
INSERT INTO centro_de_custo (nome) values ('UTI');
INSERT INTO centro_de_custo (nome) values ('Pronto Socorro');
INSERT INTO centro_de_custo (nome) values ('Higienização');
INSERT INTO centro_de_custo (nome) values ('Nutrição');
INSERT INTO centro_de_custo (nome) values ('Administrativo');
INSERT INTO centro_de_custo (nome) values ('Hospital de Taquarussu');
INSERT INTO centro_de_custo (nome) values ('Hospital Municipal de Ivinhema');
INSERT INTO centro_de_custo (nome) values ('Hospital ');
