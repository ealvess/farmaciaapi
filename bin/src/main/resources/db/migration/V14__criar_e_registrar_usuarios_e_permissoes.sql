CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	ativo BOOLEAN DEFAULT true
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha, tipo, ativo) values (1, 'Administrador', 'admin@gmail.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 'Farmacêutico', true);
INSERT INTO usuario (codigo, nome, email, senha, tipo, ativo) values (2, 'Pedro Silva', 'pedro@gmail.com', '$2y$12$koo2eLpxgttzQP.X.IzMNOq8rPxr2LyBEkMx/UPZfZdBMpebvlAKe', 'Atendente', true);

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA_DE_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_REMOVER_CATEGORIA_DE_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_PESQUISAR_CATEGORIA_DE_MEDICAMENTO');

INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_CADASTRAR_CENTRO_DE_CUSTO');
INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_REMOVER_CENTRO_DE_CUSTO');
INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_PESQUISAR_CENTRO_DE_CUSTO');

INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_CADASTRAR_CATEGORIA_DE_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_REMOVER_CATEGORIA_DE_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_PESQUISAR_CATEGORIA_DE_CORRELATO');

INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_CADASTRAR_FORNECEDOR');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_REMOVER_FORNECEDOR');
INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_PESQUISAR_FORNECEDOR');

INSERT INTO permissao (codigo, descricao) values (13, 'ROLE_CADASTRAR_ENTRADA_DE_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (14, 'ROLE_REMOVER_ENTRADA_DE_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (15, 'ROLE_PESQUISAR_ENTRADA_DE_CORRELATO');

INSERT INTO permissao (codigo, descricao) values (16, 'ROLE_CADASTRAR_ITEM_SAIDA_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (17, 'ROLE_PESQUISAR_ITEM_SAIDA_CORRELATO');

INSERT INTO permissao (codigo, descricao) values (18,'ROLE_CADASTRAR_ITEM_SAIDA_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (19,'ROLE_PESQUISAR_ITEM_SAIDA_MEDICAMENTO');

INSERT INTO permissao (codigo, descricao) values (20, 'ROLE_CADASTRAR_ENTRADA_DE_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (21, 'ROLE_REMOVER_ENTRADA_DE_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (22, 'ROLE_PESQUISAR_ENTRADA_DE_MEDICAMENTO');

INSERT INTO permissao (codigo, descricao) values (23, 'ROLE_CADASTRAR_MEDICO');
INSERT INTO permissao (codigo, descricao) values (24, 'ROLE_REMOVER_MEDICO');
INSERT INTO permissao (codigo, descricao) values (25, 'ROLE_PESQUISAR_MEDICO');

INSERT INTO permissao (codigo, descricao) values (26, 'ROLE_CADASTRAR_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (27, 'ROLE_REMOVER_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (28, 'ROLE_PESQUISAR_PACIENTE');

INSERT INTO permissao (codigo, descricao) values (29, 'ROLE_CADASTRAR_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (30, 'ROLE_REMOVER_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (31, 'ROLE_PESQUISAR_MEDICAMENTO');

INSERT INTO permissao (codigo, descricao) values (32, 'ROLE_CADASTRAR_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (33, 'ROLE_REMOVER_CORRELATO');
INSERT INTO permissao (codigo, descricao) values (34, 'ROLE_PESQUISAR_CORRELATO');

INSERT INTO permissao (codigo, descricao) values (35, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (36, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (codigo, descricao) values (37, 'ROLE_PESQUISAR_USUARIO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 13);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 14);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 16);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 17);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 20);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 21);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 22);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 23);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 24);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 25);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 26);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 27);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 28);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 29);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 30);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 31);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 32);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 33);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 34);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 35);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 36);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 37);

-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 12);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 16);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 17);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 22);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 25);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 28);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 31);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 34);
