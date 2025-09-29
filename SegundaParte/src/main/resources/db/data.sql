INSERT INTO tb_users (id, name, age, password, login,email, ativado) VALUES (UUID(),'Ana Silva', 30, 'senha123', 'ana.silva','ana@email.com','F');
INSERT INTO tb_users (id, name, age, password, login,email, ativado) VALUES (UUID(),'João Santos', 25, 'senha456', 'joao.santos','joao@email.com','F');


insert into cozinha (id, nome) values (1,'Tailandesa');
insert into cozinha (id, nome) values (2,'Indiana');

insert into restaurante (nome, taxa_frete) values ('Thai Gourmet', 10,1);
insert into restaurante (nome, taxa_frete) values ('Thai Delivery', 9.50,2);
insert into restaurante (nome, taxa_frete) values ('Tuk Tuk Comida Indiana', 15,1);