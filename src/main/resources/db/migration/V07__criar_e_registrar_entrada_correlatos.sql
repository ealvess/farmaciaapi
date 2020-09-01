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

INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (1, '2020/09/01', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (1, '2020/09/02', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (1, '2020/09/04', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (2, '2020/09/06', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2020/09/08', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (3, '2020/09/10', '1', '00299773', '2018/10/08', '2022/10/08', '1234A1','5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario)VALUES  (4, '2020/09/11', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (5, '2020/09/13', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (6, '2020/09/15', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (3, '2020/09/16', '10', '00299773', '2018/06/08', '2022/06/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (2, '2020/09/17', '10', '00299773', '2018/08/08', '2022/08/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (7, '2020/09/19', '10', '00299773', '2018/08/08', '2022/08/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (8, '2020/09/20', '10', '00299773', '2018/08/08', '2022/08/08', '1234A1','3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (9, '2020/09/21', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (10, '2020/09/23', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES (13, '2020/09/25', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario)VALUES  (12, '2020/09/25', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (15, '2020/09/26', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (16, '2020/09/27', '11', '00299773', '2018/08/08', '2022/08/08', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (19, '2020/09/28', '11', '00299773', '2018/08/09', '2022/08/09', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (10, '2020/09/29', '11', '00299773', '2018/08/03', '2022/08/03', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (15, '2020/09/29', '11', '00299773', '2018/08/02', '2022/08/02', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (16, '2020/09/30', '11', '00299773', '2018/08/06', '2022/08/06', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (23, '2020/09/30', '11', '00299773', '2018/08/06', '2022/08/06', '1234A1','100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, lote, quantidade, valor_unitario) VALUES  (24, '2020/09/30', '11', '00299773', '2018/08/12', '2022/08/12', '1234A1','100', '7.65');
