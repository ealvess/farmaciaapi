CREATE TABLE saida_medicamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_paciente BIGINT(20),
	codigo_entrada_medicamento BIGINT(20),
	data_saida DATE NOT NULL,
	quantidade VARCHAR(20),
	valor_unitario FLOAT,
	total FLOAT,
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_entrada_medicamento) REFERENCES entrada_medicamento(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('1', '1', '2020/09/03', '10', '5.50', '55');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('3', '1', '2020/09/10', '6', '5.50', '33');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('5', '3', '2020/09/13', '2', '7.60', '15.2');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('5', '7', '2020/09/15', '2', '1.50', '3');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('1', '7', '2020/09/15', '2', '1.50', '3');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('2', '1', '2020/09/16', '10', '5.50', '55');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('4', '1', '2020/09/17', '6', '5.50', '33');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('7', '3', '2020/09/18', '2', '7.60', '15.2');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('8', '7', '2020/09/19', '2', '1.50', '3');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('2', '8', '2020/09/22', '2', '2.1', '4.2');
INSERT INTO saida_medicamento(codigo_paciente, codigo_entrada_medicamento, data_saida, quantidade, valor_unitario, total) VALUES ('2', '8', '2020/09/29', '2', '2.1', '4.2');
