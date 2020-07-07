CREATE TABLE descarte(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_medicamento BIGINT(20),
	codigo_correlato BIGINT(20),
	data_saida DATE NOT NULL,
	quantidade INTEGER NOT NULL,
	valor_unitario FLOAT,
	totaL FLOAT,
	motivo VARCHAR (200),
	FOREIGN KEY (codigo_medicamento) REFERENCES medicamento(codigo),
	FOREIGN KEY (codigo_correlato) REFERENCES correlato(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO descarte (codigo_medicamento, data_saida, quantidade, valor_unitario, total, motivo) values ('1', '2020/07/02', '50', '5.5', '275', 'VENCIDO');
INSERT INTO descarte (codigo_medicamento, data_saida, quantidade, valor_unitario, total, motivo) values ('2', '2020/07/02', '50', '7.6', '380', 'VENCIDO');
INSERT INTO descarte (codigo_medicamento, data_saida, quantidade, valor_unitario, total, motivo) values ('3', '2020/07/02', '50', '15.6', '780', 'VENCIDO');
