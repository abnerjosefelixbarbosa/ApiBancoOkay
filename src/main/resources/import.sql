CREATE DATABESE IF NOT EXISTS testdb CHARACTER SET UTF8mb4 COLLATE collate utf8mb4_bin;

INSERT INTO cliente (id, nome, cpf, rg, email, data_nascimento, senha_cliente, numero, cep, logradouro, bairro, cidade, estado) VALUES (1, 'Alberto Camara', '949.612.154-30', '36.825.176', 'cliente1@gmail.com', '1991-01-16', '481228', 671, '52140-090', 'Rua Barão de Pajeú', 'Dois Unidos', 'Recife', 'PE');
INSERT INTO cliente (id, nome, cpf, rg, email, data_nascimento, senha_cliente, numero, cep, logradouro, bairro, cidade, estado) VALUES (2, 'Barbara Cardoso', '370.897.974-57', '29.254.761', 'cliente2@gmail.com', '2005-01-17', '583245', 998, '50790-061', 'Travessa da Capela', 'Curado', 'Recife', 'PE');

INSERT INTO conta (id, agencia, conta, saldo, senha_conta, id_cliente) VALUES (1, '1568-1', '13681-1', 1000, '4812', 1);  
INSERT INTO conta (id, agencia, conta, saldo, senha_conta, id_cliente) VALUES (2, '2210-1', '21224-1', 400, '5832', 2);  

INSERT INTO telefone (id, telefone, id_cliente) VALUES (1, '(81) 99709-5814', 1);
INSERT INTO telefone (id, telefone, id_cliente) VALUES (2, '(81) 98458-3399', 2);