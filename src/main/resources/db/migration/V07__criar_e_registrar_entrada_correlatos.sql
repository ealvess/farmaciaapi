CREATE TABLE entrada_correlato(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_correlato BIGINT(20) NOT NULL,
	codigo_fornecedor BIGINT(20) NOT NULL,
	data_entrada DATE NOT NULL,
	numero_nota_fiscal VARCHAR(20) NOT NULL,
	data_fabricacao DATE NOT NULL,
	data_validade DATE NOT NULL,
	lote VARCHAR(30) NOT NULL,
	quantidade VARCHAR (20) NOT NULL,
	valor_unitario FLOAT NOT NULL,
	FOREIGN KEY (codigo_correlato) REFERENCES correlatos(codigo),
	FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario)VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/08/08', '2022/08/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/08/08', '2022/08/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/08/08', '2022/08/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario)VALUES  (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (4, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (5, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (9, '2021/04/08', '11', '00299773', '2018/08/09', '2022/08/09', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (10, '2021/04/08', '11', '00299773', '2018/08/03', '2022/08/03', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (15, '2021/04/08', '11', '00299773', '2018/08/02', '2022/08/02', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (16, '2021/04/08', '11', '00299773', '2018/08/06', '2022/08/06', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (23, '2021/04/08', '11', '00299773', '2018/08/06', '2022/08/06', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (24, '2021/04/08', '11', '00299773', '2018/08/12', '2022/08/12', '1234A1','100', '7.65');
