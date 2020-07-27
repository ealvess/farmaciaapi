CREATE TABLE correlato(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_grupo BIGINT(20) NOT NULL,
	codigo_fornecedor BIGINT(20) NOT NULL,
	data_entrada DATE NOT NULL,
	numero_nota_fiscal VARCHAR(20) NOT NULL,
	nome_correlato VARCHAR(100) NOT NULL,
	apresentacao VARCHAR (20) NOT NULL,
	data_fabricacao DATE NOT NULL,
	data_validade DATE NOT NULL,
	quantidade VARCHAR (20) NOT NULL,
	valor_unitario FLOAT NOT NULL,
	FOREIGN KEY (codigo_grupo) REFERENCES grupo_correlato(codigo),
	FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', 'AGULHA HIPODÉRMICA 13 X 0,3 A 13 X 0,38 MM', 'UNIDADE', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', 'AGULHA HIPODÉRMICA 25 X 0,7 MM', 'UNIDADE', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', 'AGULHA HIPODÉRMICA 25 X 0,8 MM', 'UNIDADE', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (1, '2019/04/08', '1', '00299773', 'AGULHA HIPODÉRMICA 30 X 0,7 MM', 'UNIDADE', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', 'AGULHA HIPODÉRMICA 30 X 0,8 MM', 'UNIDADE', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (1, '2019/04/08', '1', '00299773', 'AGULHA HIPODÉRMICA 40 X 1,2 MM', 'UNIDADE', '2018/10/08', '2022/10/08', '5000', '1.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 7,5', 'PAR', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 8,0', 'PAR', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA CIRÚRGICA EM BORRACHA SINTÉTICA, TAMANHO 8,5', 'PAR', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA CIRÚRGICA EM LÁTEX NATURAL, TAMANHO 6,5', 'PAR', '2018/06/08', '2022/06/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA PARA PROCEDIMENTO EM BORRACHA NITRÍLICA, TAMANHO PEQUENO (P)', 'CAIXA', '2018/08/08', '2022/08/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA PARA PROCEDIMENTO EM BORRACHA NITRÍLICA, TAMANHO PEQUENO (M)', 'CAIXA', '2018/08/08', '2022/08/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (2, '2021/04/08', '10', '00299773', 'LUVA PARA PROCEDIMENTO EM BORRACHA NITRÍLICA, TAMANHO PEQUENO (G)', 'CAIXA', '2018/08/08', '2022/08/08', '3000', '2.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (3, '2021/04/08', '11', '00299773', 'CÂNULA OROFARÍNGEA (TIPO GUEDEL) DE No 00', 'UNIDADE', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (3, '2021/04/08', '11', '00299773', 'CÂNULA OROFARÍNGEA (TIPO GUEDEL) DE No 1', 'UNIDADE', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES (3, '2021/04/08', '11', '00299773', 'CÂNULA OROFARÍNGEA (TIPO GUEDEL) DE No 3', 'UNIDADE', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (3, '2021/04/08', '11', '00299773', 'CÂNULA DE TRAQUEOSTOMIA No 4,0 COM BALÃO', 'UNIDADE', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (4, '2021/04/08', '11', '00299773', 'BOBINA PARA ESTERILIZAÇÃO', 'UNIDADE', '2018/08/08', '2022/08/08', '100', '7.65');
INSERT INTO correlato (codigo_grupo, data_entrada, codigo_fornecedor, numero_nota_fiscal, nome_correlato, apresentacao, data_fabricacao, data_validade, quantidade, valor_unitario) VALUES  (5, '2021/04/08', '11', '00299773', 'COMPRESSA ALGODONADA ESTÉRIL, 10 X 15 CM', 'UNIDADE', '2018/08/08', '2022/08/08', '100', '7.65');
