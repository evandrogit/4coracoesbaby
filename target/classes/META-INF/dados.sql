INSERT INTO usuarios(id,contato,cpf,funcao,login,nome,senha,urlimagem,empresa) VALUES (1,'(92) 9-98458-2975','991.162.473-87','CEO','admin','Administrador','$2a$10$vYUtJb2mr9J.UQwJ75XEBOsQ6XpCIIWzgFDnvZqklK6ceWZxmDsoq','https://i.imgur.com/PIMNNut.jpg','EMPRESA');
INSERT INTO usuarios(id,contato,cpf,funcao,login,nome,senha,urlimagem,empresa) VALUES (2,'(92) 9-98458-2975','959.372.267-87','Atendente','user','Atendente','$2a$10$vYUtJb2mr9J.UQwJ75XEBOsQ6XpCIIWzgFDnvZqklK6ceWZxmDsoq','https://i.imgur.com/PIMNNut.jpg','EMPRESA');

INSERT INTO grupos(id,descricao,nome) VALUES (1,'Administrador','ADMINISTRADOR');
INSERT INTO grupos(id,descricao,nome) VALUES (2,'Usuário Avançado','USUARIO_AVANCADO');
INSERT INTO grupos(id,descricao,nome) VALUES (3,'Usuário Comum','USUARIO_COMUM');
INSERT INTO grupos(id,descricao,nome) VALUES (4,'Vendedor','VENDEDOR');

INSERT INTO usuario_grupo(usuario_id,grupo_id) VALUES (1,1);
INSERT INTO usuario_grupo(usuario_id,grupo_id) VALUES (2,4);

INSERT INTO categoria_produtos(id,nome,empresa) VALUES (1,'Porta','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (3,'Fechadura','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (2,'Aduelas','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (4,'Molduras','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (6,'Espuma','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (8,'Trilho','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (10,'Puxador','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (5,'Dobradiças','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (7,'Kit Padrão','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (153,'Batedor','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (154,'Vista','EMPRESA');
INSERT INTO categoria_produtos(id,nome,empresa) VALUES (9,'Roudana','EMPRESA');

INSERT INTO fornecedores(id,contato,nome,empresa) VALUES (1,'(92) 9-9252-5903','Madeforming','EMPRESA');
INSERT INTO fornecedores(id,contato,nome,empresa) VALUES (2,'(22) 9-9229-7261','Stam','EMPRESA');
INSERT INTO fornecedores(id,contato,nome,empresa) VALUES (11,NULL,'Marcelo','EMPRESA');
INSERT INTO fornecedores(id,contato,nome,empresa) VALUES (12,NULL,'Avelino','EMPRESA');


INSERT INTO bairros(id,nome,zona) VALUES (1,'Adrianápolis','CENTRO_SUL');
INSERT INTO bairros(id,nome,zona) VALUES (2,'Aleixo','CENTRO_SUL');
INSERT INTO bairros(id,nome,zona) VALUES (3,'Alvorada','CENTRO_OESTE');
INSERT INTO bairros(id,nome,zona) VALUES (4,'Armando Mendes','LESTE');
INSERT INTO bairros(id,nome,zona) VALUES (5,'Betânia','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (6,'Cachoeirinha','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (7,'Centro','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (8,'Chapada','CENTRO_SUL');
INSERT INTO bairros(id,nome,zona) VALUES (9,'Cidade de Deus','NORTE');
INSERT INTO bairros(id,nome,zona) VALUES (10,'Cidade Nova','NORTE');
INSERT INTO bairros(id,nome,zona) VALUES (11,'Colônia Antônio Aleixo','LESTE');
INSERT INTO bairros(id,nome,zona) VALUES (12,'Colônia Oliveira Machado','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (13,'Colônia Santo Antônio','NORTE');
INSERT INTO bairros(id,nome,zona) VALUES (14,'Colônia Terra Nova','NORTE');
INSERT INTO bairros(id,nome,zona) VALUES (15,'Compensa','OESTE');
INSERT INTO bairros(id,nome,zona) VALUES (16,'Coroado','LESTE');
INSERT INTO bairros(id,nome,zona) VALUES (17,'Crespo','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (18,'Da Paz','CENTRO_OESTE');
INSERT INTO bairros(id,nome,zona) VALUES (19,'Distrito Industrial I','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (20,'Distrito Industrial II','LESTE');
INSERT INTO bairros(id,nome,zona) VALUES (21,'Dom Pedro','CENTRO_OESTE');
INSERT INTO bairros(id,nome,zona) VALUES (22,'Educandos','SUL');
INSERT INTO bairros(id,nome,zona) VALUES (23,'Flores','CENTRO_SUL');
INSERT INTO bairros(id,nome,zona) VALUES (24,'Gilberto Mestrinho','LESTE');
INSERT INTO bairros(id,nome,zona) VALUES (25,'Glória','OESTE');


INSERT INTO tipos_vendas(id,descricao) VALUES (1,'Olx');
INSERT INTO tipos_vendas(id,descricao) VALUES (2,'Face');
INSERT INTO tipos_vendas(id,descricao) VALUES (33,'Loja');
INSERT INTO tipos_vendas(id,descricao) VALUES (34,'Cliente');
INSERT INTO tipos_vendas(id,descricao) VALUES (35,'Indicação');
INSERT INTO tipos_vendas(id,descricao) VALUES (36,'Instagran');
INSERT INTO tipos_vendas(id,descricao) VALUES (37,'Site');
INSERT INTO tipos_vendas(id,descricao) VALUES (3009,'Não Informado');

INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (42,'Receitas',1);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (40,'Custos',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (38,'Despesa fixa',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (39,'Despesa variável',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (41,'Investimentos',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (4288,'Ajuste de Caixa',0);

INSERT INTO destino_lancamentos(id,descricao) VALUES (43,'Empresa');

INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (176,'Água',43,38,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (177,'Luz',43,38,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (37565,'Aporte',47,42,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (180,'Aluguel',43,38,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (183,'Manutenção de Veículos',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (186,'Manutenção de Equipamentos',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (185,'Despesas com alimentação e mantimentos',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (190,'Aquisição de bens e equipamentos',43,40,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25832,'Avarias',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25833,'Combustível',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25834,'Custos e viagens a negócios',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25836,'Despesas com saúde',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25837,'Empréstimos',43,41,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25838,'Frete de veículos',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25839,'Impostos',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25840,'Limpeza e serviços gerais',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25841,'Mão de obra com entregas',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25843,'Material e serviço de construção',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25844,'Material indireto',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25850,'Outras receitas',43,42,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25851,'Propaganda e marketing',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25853,'Segurança',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25854,'Serviço de manutenção em geral',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (178,'Telefone, internet e informática',43,38,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25855,'Transporte',43,38,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25835,'Salários',43,40,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (1447,'Outras despesas',43,39,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (4289,'Ajuste de Caixa',43,4288,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (4437,'Refeição',43,38,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (5424,'Décimo',43,40,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (5425,'Férias',43,40,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (5426,'Bônus',43,40,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (5427,'PLR',43,40,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (5423,'Hora Extra',43,40,'EMPRESA');