INSERT INTO tb_users (id, name, age, password, login,email, ativado) VALUES (UUID(),'Ana Silva', 30, 'senha123', 'ana.silva','ana@email.com','F');
INSERT INTO tb_users (id, name, age, password, login,email, ativado) VALUES (UUID(),'João Santos', 25, 'senha456', 'joao.santos','joao@email.com','F');

insert into tb_cozinha (id, nome) values (1,'Tailandesa');
insert into tb_cozinha (id, nome) values (2,'Indiana');

INSERT INTO tb_estado (nome) VALUES ('Acre');
INSERT INTO tb_estado (nome) VALUES ('Alagoas');
INSERT INTO tb_estado (nome) VALUES ('Amapá');
INSERT INTO tb_estado (nome) VALUES ('Amazonas');
INSERT INTO tb_estado (nome) VALUES ('Bahia');
INSERT INTO tb_estado (nome) VALUES ('Ceará');
INSERT INTO tb_estado (nome) VALUES ('Distrito Federal');
INSERT INTO tb_estado (nome) VALUES ('Espírito Santo');
INSERT INTO tb_estado (nome) VALUES ('Goiás');
INSERT INTO tb_estado (nome) VALUES ('Maranhão');
INSERT INTO tb_estado (nome) VALUES ('Mato Grosso');
INSERT INTO tb_estado (nome) VALUES ('Mato Grosso do Sul');
INSERT INTO tb_estado (nome) VALUES ('Minas Gerais');
INSERT INTO tb_estado (nome) VALUES ('Pará');
INSERT INTO tb_estado (nome) VALUES ('Paraíba');
INSERT INTO tb_estado (nome) VALUES ('Paraná');
INSERT INTO tb_estado (nome) VALUES ('Pernambuco');
INSERT INTO tb_estado (nome) VALUES ('Piauí');
INSERT INTO tb_estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO tb_estado (nome) VALUES ('Rio Grande do Norte');
INSERT INTO tb_estado (nome) VALUES ('Rio Grande do Sul');
INSERT INTO tb_estado (nome) VALUES ('Rondônia');
INSERT INTO tb_estado (nome) VALUES ('Roraima');
INSERT INTO tb_estado (nome) VALUES ('Santa Catarina');
INSERT INTO tb_estado (nome) VALUES ('São Paulo');
INSERT INTO tb_estado (nome) VALUES ('Sergipe');
INSERT INTO tb_estado (nome) VALUES ('Tocantins');

INSERT INTO tb_cidade (estado_id, id, nome) VALUES(6, 1, 'Maranguape');

insert into tb_restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10,1);
insert into tb_restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50,2);
insert into tb_restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15,1);

INSERT INTO tb_restaurante (taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome) VALUES (7, 1, 1, 'Novo Mpe', '61942250', 'Bloco A', 'Rua Nove', '360', 'Brasilis');

INSERT INTO tb_forma_pagamento (descricao) VALUES ('Cartão de crédito'), ('Cartão de débito'), ('Dinheiro');

INSERT INTO restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (1,1),(1,2),(1,3),(2,1),(1,2),(2,3),(3,1),(3,2);
