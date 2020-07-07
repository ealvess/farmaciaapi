CREATE TABLE medicamento( 
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
	codigo_categoria BIGINT(20) NOT NULL, 
	data_entrada DATE NOT NULL, 
	codigo_fornecedor BIGINT(20) NOT NULL,
	numero_nota_fiscal VARCHAR(30) NOT NULL, 
	nome_medicamento VARCHAR(50) NOT NULL,
	apresentacao_medicamento VARCHAR(50) NOT NULL,
	volume VARCHAR(10),
	data_fabricacao DATE NOT NULL, 
	validade DATE NOT NULL, 
	lote VARCHAR (30) NOT NULL, 
	quantidade VARCHAR(15) NOT NULL, 
	valor_unitario FLOAT NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo), 
	FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (1,DATE '2020/01/23','16','0098767','Lidocaína 2% gel', 'BISNAGA', '30g', DATE '2019/04/05',DATE '2021/04/05','16E8719','500','5.50');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (1, DATE '2020//01/23', '16', '0098767', 'Lidocaína 2% Líquida', 'FRASCO-AMPOLA', '20ml', DATE '2019/07/08',DATE '2021/07/08', '080720B19', '800', '7.60');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (1, DATE'2020/01/23', '16', '9846353', 'Etomidato 2mg/ml', 'FRASCO-APOLA', '20ml', DATE '2019/03/10', DATE '2021/03/10', '0345670B19', '200', '15.60');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (1,DATE'2020/01/23', '16', '9846353', 'Ketamina 10mg/ml', 'FRASCO-AMPOLA', '20ml', DATE '2019/03/10', DATE '2021/03/10', '3435567CB00', '200', '15.60');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (2, DATE '2020/01/23', '16', '9846353', 'Bupivacaina', 'FRASCO-AMPOLA', '20ml', DATE '2019/03/19', DATE '2021/03/19', '37CB00', '200', '12.60');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (3, DATE'2020/01/23', '10', '9846353', 'Carbamazepina Comprimido', 'COMPRIMIDO', '', DATE'2019/03/1', DATE'2021/03/1', '3435567CB00', '200', '1.60');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (3, DATE'2020/01/23', '10', '9846353', 'Clonazepan', 'COMPRIMIDO', '', DATE '2019/03/10', DATE'2021/03/10', '3435567CB00', '200', '1.50');
INSERT INTO medicamento(codigo_categoria, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_medicamento, apresentacao_medicamento, volume, data_fabricacao, validade, lote, quantidade, valor_unitario) VALUES (4,DATE'2020/01/23', '10', '9846353','Bromazepan comprimido', 'COMPRIMIDO', '', DATE '2019/03/10', DATE'2021/03/10', '34355sCB00', '200', '2.10');
