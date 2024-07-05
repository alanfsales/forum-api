insert into perfil (id, nome)
values(1, 'ALUNO');

insert into usuario (id, nome, email, senha, perfil_id)
values(1, 'teste', 'teste@email.com', '$2a$12$q3L5y9/29NSmw41v3p29fOi4LrZI7gr.jw0POsJqSXL1zjKeSX2sS', 1);

insert into usuario (id, nome, email, senha, perfil_id)
values(2, 'teste2', 'teste2@email.com', '$2a$12$q3L5y9/29NSmw41v3p29fOi4LrZI7gr.jw0POsJqSXL1zjKeSX2sS', 1);

insert curso (id, nome, categoria)
values(1, 'Java', 'Programação');