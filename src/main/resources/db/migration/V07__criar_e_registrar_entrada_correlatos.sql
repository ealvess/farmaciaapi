CREATE TABLE entrada_correlato(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_correlato BIGINT(20) NOT NULL,
	codigo_fornecedor BIGINT(20) NOT NULL,
	data_entrada DATE NOT NULL,
	numero_nota_fiscal VARCHAR(20) NOT NULL,
	data_fabricacao DATE NOT NULL,
	data_validade DATE NOT NULL,
	quantidade VARCHAR (20) NOT NULL,
	valor_unitario FLOAT NOT NULL,
	FOREIGN KEY (codigo_correlato) REFERENCES correlatos(codigo),
	FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario)VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/08/08', '2022/08/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/08/08', '2022/08/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', '2018/08/08', '2022/08/08', '3000', '2.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario)VALUES  (3, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (4, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO entrada_correlato (codigo_correlato, data_entrada, codigo_fornecedor, numero_nota_fiscal, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (5, '2021/04/08', '11', '00299773', '2018/08/08', '2022/08/08', '100', '7.65');
