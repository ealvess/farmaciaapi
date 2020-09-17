CREATE TABLE entrada_medicamento( 
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
	codigo_medicamento BIGINT(20) NOT NULL, 
	data_entrada DATE NOT NULL, 
	codigo_fornecedor BIGINT(20) NOT NULL,
	numero_nota_fiscal VARCHAR(30) NOT NULL, 
	data_fabricacao DATE NOT NULL, 
	validade DATE NOT NULL, 
	lote VARCHAR (30) NOT NULL, 
	quantidade VARCHAR(15) NOT NULL, 
	valor_unitario FLOAT NOT NULL,
	FOREIGN KEY (codigo_medicamento) REFERENCES medicamentos(codigo),
	FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (1, '2020-09-01','16','0098767', '2019/04/05', '2021/04/05','16E8719','500','5.50');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (2, '2020-09-05', '16', '0098767', '2019/07/08', '2021/07/08', '080720B19', '800', '7.60');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario)  VALUES (3, '2020-09-10', '16', '9846353', '2019/03/10', '2021/03/10', '0345670B19', '200', '15.60');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario)  VALUES (4, '2020-09-15', '16', '9846353',  '2019/03/10', '2021/03/10', '3435567CB00', '200', '15.60');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario)  VALUES (5,  '2020-09-20', '16', '9846353', '2019/03/19', '2021/03/19', '37CB00', '200', '12.60');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (6, '2020-09-23', '10', '9846353', '2019/03/1', '2021/03/1', '3435567CB00', '200', '1.60');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario)  VALUES (7, '2020-09-25', '10', '9846353', '2019/03/1', '2021/03/10', '3435567CB00', '200', '1.50');
INSERT INTO entrada_medicamento(codigo_medicamento, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (8, '2020-07-30', '10', '9846353', '2019/03/10', '2021/03/10', '34355sCB00', '200', '2.10');
