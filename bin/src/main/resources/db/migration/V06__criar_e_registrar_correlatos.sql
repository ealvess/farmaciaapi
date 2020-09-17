CREATE TABLE correlatos(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_categoria BIGINT(20) NOT NULL,
	nome VARCHAR(300) NOT NULL,
	unidade_de_medida VARCHAR(50) NOT NULL,
	quantidade_minima INTEGER,
	localizacao VARCHAR(50),
	ativo BOOLEAN NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categorias_correlato(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 13 X 0,3', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 13 X 0,45', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 20 X 0,55', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 25 X 0,7', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 20 X 0,8', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 30 X 0,7', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 30 X 0,8', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('AGULHA HIPODÉRMICA 40 X 1,2', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('CATÉTER INTRAVENOSO PERIFÉRICO, Nº14', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('CATÉTER INTRAVENOSO PERIFÉRICO, Nº16', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('CATÉTER INTRAVENOSO PERIFÉRICO, Nº18', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('CATÉTER INTRAVENOSO PERIFÉRICO, Nº20', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('CATÉTER INTRAVENOSO PERIFÉRICO, Nº22', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('CATÉTER INTRAVENOSO PERIFÉRICO, Nº24', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA DESCARTÁVEL 01 ML', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA PARA INSULINA 01 ML S/ AGULHA', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA DESCARTÁVEL 03 ML COM BICO', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA DESCARTÁVEL 05 ML COM BICO', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA DESCARTÁVEL 10 ML COM BICO', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA DESCARTÁVEL 20 ML COM BICO', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('SERINGA DESCARTÁVEL 60 ML COM BICO', 1, 'UNIDADE', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 7,5', 2, 'PAR', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 8,0', 2, 'PAR', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 8,5', 2, 'PAR', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 6,5', 2, 'PAR', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 7,0', 2, 'PAR', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA PARA PROCEDIMENTO EM BORRACHA NITRÍLICA, TAMANHO PEQUENO (P)', 2, 'CAIXA', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA PARA PROCEDIMENTO EM BORRACHA NITRÍLICA, TAMANHO MÉDIO (M)', 2, 'CAIXA', 1000, true);
INSERT INTO correlatos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LUVA PARA PROCEDIMENTO EM BORRACHA NITRÍLICA, TAMANHO GRANDE (G)', 2, 'CAIXA', 1000, true);
