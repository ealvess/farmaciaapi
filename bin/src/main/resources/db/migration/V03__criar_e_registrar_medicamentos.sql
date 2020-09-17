CREATE TABLE medicamentos(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_categoria BIGINT(20) NOT NULL,
	nome VARCHAR(100) NOT NULL,
	unidade_de_medida VARCHAR(50) NOT NULL,
	quantidade_minima INTEGER,
	localizacao VARCHAR(50),
	ativo BOOLEAN NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('DIPIRONA 500MG', 1, 'COMPRIMIDO', 300, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('DIPIRONA 50MG', 1, 'FRASCO GOTAS', 300, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('HEXOMEDINE', 1, 'FRASCO', 100, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('PARACETAMOL 500MG', 1, 'COMPRIMIDO', 1000, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('PARACETAMOL 750MG', 1, 'COMPRIMIDO', 1000, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('ANESTÉSICO COLIRIO', 5, 'FRASCO GOTAS', 50, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LIDOCAINA 10% SPRAY', 5, 'FRASCO', 50, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LIDOCAINA 2% C/ VASOCONSTRUTOR', 5, 'AMPOLA', 200, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LIDOCAINA GELÉIA - POMADA', 5, 'BISNAGA', 500, true);
INSERT INTO medicamentos (nome, codigo_categoria, unidade_de_medida, quantidade_minima, ativo) values ('LIDOCAINA 2% S/ VASO(EPINEFRINA) 20ML', 5, 'FRASCO/AMPOLA', 200, true);
