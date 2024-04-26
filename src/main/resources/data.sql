INSERT INTO EPIC_USER (id, name, email, avatar_url)
VALUES (99, 'Maria da Silva', 'maria@fiap.com.br', 'https://i.pravatar.cc/150?img=5');

INSERT INTO TASK (title, description, score, status, user_id) 
VALUES ('Teste', 'Criar os testes unitários', 10, 0, 99);

INSERT INTO TASK (title, description, score, status) 
VALUES ('BD', 'Criar os diagrasmas do banco', 50, 10);

INSERT INTO TASK (title, description, score, status) 
VALUES ('Figma', 'Prototipar as telas do app', 70, 25);