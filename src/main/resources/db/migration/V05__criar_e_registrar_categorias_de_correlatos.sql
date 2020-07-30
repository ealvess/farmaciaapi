CREATE TABLE categorias_correlato(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categorias_correlato (nome, ativo) values ('Agulhas, Seringas e Cateteres', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Luvas', true);
INSERT INTO categorias_correlato (nome, ativo) values ('CÂnulas, Drenos, Tubos e sondas', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Saneantes, Antissepticos e CME', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Paramentação Cirúrgica', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Materia Médico Hospitalar de uso geral - MMH', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Embalagens Farmaêuticas', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Fios Cirúrgicos e Hemostaticos', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Material respiratório', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Material permanente médico hospitalar', true);
INSERT INTO categorias_correlato (nome, ativo) values ('Curativo', true);

