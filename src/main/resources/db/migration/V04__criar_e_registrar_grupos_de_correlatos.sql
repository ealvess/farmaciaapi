CREATE TABLE grupo_correlato(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO grupo_correlato (nome) values ('Agulhas, Seringas e Cateteres');
INSERT INTO grupo_correlato (nome) values ('Luvas');
INSERT INTO grupo_correlato (nome) values ('CÂnulas, Drenos, Tubos e sondas');
INSERT INTO grupo_correlato (nome) values ('Saneantes, Antissepticos e CME');
INSERT INTO grupo_correlato (nome) values ('Paramentação Cirúrgica');
INSERT INTO grupo_correlato (nome) values ('Materia Médico Hospitalar de uso geral - MMH');
INSERT INTO grupo_correlato (nome) values ('Embalagens Farmaêuticas');
INSERT INTO grupo_correlato (nome) values ('Fios Cirúrgicos e Hemostaticos');
INSERT INTO grupo_correlato (nome) values ('Material respiratório');
INSERT INTO grupo_correlato (nome) values ('Material permanente médico hospitalar');
INSERT INTO grupo_correlato (nome) values ('Curativo');

