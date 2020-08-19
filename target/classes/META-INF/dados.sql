INSERT INTO usuarios(id,contato,cpf,funcao,login,nome,senha,urlimagem,empresa) VALUES (1,'(92) 9-98458-2975','991.162.473-87','CEO','admin','Administrador','$2a$10$vYUtJb2mr9J.UQwJ75XEBOsQ6XpCIIWzgFDnvZqklK6ceWZxmDsoq','https://i.imgur.com/PIMNNut.jpg','EMPRESA');
INSERT INTO usuarios(id,contato,cpf,funcao,login,nome,senha,urlimagem,empresa) VALUES (2,'(92) 9-98458-2975','959.372.267-87','Atendente','user','Atendente','$2a$10$vYUtJb2mr9J.UQwJ75XEBOsQ6XpCIIWzgFDnvZqklK6ceWZxmDsoq','https://i.imgur.com/PIMNNut.jpg','EMPRESA');

INSERT INTO grupos(id,descricao,nome) VALUES (1,'Administrador','ADMINISTRADOR');
INSERT INTO grupos(id,descricao,nome) VALUES (2,'Usuário Avançado','USUARIO_AVANCADO');
INSERT INTO grupos(id,descricao,nome) VALUES (3,'Usuário Comum','USUARIO_COMUM');
INSERT INTO grupos(id,descricao,nome) VALUES (4,'Vendedor','VENDEDOR');

INSERT INTO usuario_grupo(usuario_id,grupo_id) VALUES (1,1);
INSERT INTO usuario_grupo(usuario_id,grupo_id) VALUES (2,4);

INSERT INTO categoria_produtos(id,nome,empresa) VALUES (1,'Body','EMPRESA');

INSERT INTO fornecedores(id,contato,nome,empresa) VALUES (3,NULL,'Torra Torra','EMPRESA');

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
INSERT INTO tipos_vendas(id,descricao) VALUES (3,'Loja');
INSERT INTO tipos_vendas(id,descricao) VALUES (4,'Cliente');
INSERT INTO tipos_vendas(id,descricao) VALUES (5,'Indicação');
INSERT INTO tipos_vendas(id,descricao) VALUES (6,'Instagran');
INSERT INTO tipos_vendas(id,descricao) VALUES (7,'Site');
INSERT INTO tipos_vendas(id,descricao) VALUES (8,'Não Informado');

INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (1,'Receitas',1);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (2,'Custos',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (3,'Despesa fixa',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (4,'Despesa variável',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (5,'Investimentos',0);
INSERT INTO tipos_lancamentos(id,descricao,origem) VALUES (6,'Ajuste de Caixa',0);

INSERT INTO destino_lancamentos(id,descricao) VALUES (1,'Empresa');

INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (1,'Água',1,3,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (2,'Luz',1,3,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (3,'Aporte',1,1,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (4,'Aluguel',1,3,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (5,'Manutenção de Veículos',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (6,'Manutenção de Equipamentos',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (7,'Despesas com alimentação e mantimentos',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (8,'Aquisição de bens e equipamentos',1,2,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (9,'Avarias',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (10,'Combustível',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (11,'Custos e viagens a negócios',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (12,'Despesas com saúde',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (13,'Empréstimos',1,5,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (14,'Frete de veículos',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (15,'Impostos',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (16,'Limpeza e serviços gerais',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (17,'Mão de obra com entregas',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (18,'Material e serviço de construção',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (19,'Material indireto',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (20,'Outras receitas',1,1,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (21,'Propaganda e marketing',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (22,'Segurança',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (23,'Serviço de manutenção em geral',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (24,'Telefone, internet e informática',1,3,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (25,'Transporte',1,3,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (26,'Salários',1,2,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (27,'Outras despesas',1,4,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (28,'Ajuste de Caixa',1,6,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (29,'Refeição',1,3,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (30,'Décimo',1,2,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (31,'Férias',1,2,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (32,'Bônus',1,2,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (33,'PLR',1,2,'EMPRESA');
INSERT INTO categoria_lancamentos(id,nome,destinolancamento_id,tipolancamento_id,empresa) VALUES (34,'Hora Extra',1,2,'EMPRESA');