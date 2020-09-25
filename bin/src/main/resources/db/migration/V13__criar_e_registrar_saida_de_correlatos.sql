CREATE TABLE saida_correlato(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_centro_de_custo BIGINT(20),
	codigo_entrada_correlato BIGINT(20),
	data_saida DATE NOT NULL,
	quantidade VARCHAR(20),
	valor_unitario FLOAT,
	total FLOAT,
	FOREIGN KEY (codigo_centro_de_custo) REFERENCES centro_de_custo(codigo),
	FOREIGN KEY (codigo_entrada_correlato) REFERENCES entrada_correlato(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO saida_correlato(codigo_centro_de_custo, codigo_entrada_correlato, data_saida, quantidade, valor_unitario, total) VALUES ('1', '1', '2020/09/03', '100', '1.65', '165');
INSERT INTO saida_correlato(codigo_centro_de_custo, codigo_entrada_correlato, data_saida, quantidade, valor_unitario, total) VALUES ('1', '1', '2020/09/14', '100', '1.65', '165');
INSERT INTO saida_correlato(codigo_centro_de_custo, codigo_entrada_correlato, data_saida, quantidade, valor_unitario, total) VALUES ('1', '2', '2020/09/22', '100', '1.65', '165');
INSERT INTO saida_correlato(codigo_centro_de_custo, codigo_entrada_correlato, data_saida, quantidade, valor_unitario, total) VALUES ('3', '3', '2020/09/30', '100', '1.65', '165');
