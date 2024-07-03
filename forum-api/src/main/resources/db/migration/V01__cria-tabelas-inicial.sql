create table curso(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    categoria varchar(100) not null,

    primary key(id)
);

create table perfil(

    id bigint not null auto_increment,
    nome varchar(100) not null,

    primary key(id)
);

create table usuario(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    perfil_id bigint not null,

    primary key(id),
    constraint fk_usuario_perfil_id foreign key(perfil_id) references perfil(id)
);

create table topico(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(255) not null,
    data_criacao datetime not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_totipo_autor_id foreign key(autor_id) references usuario(id),
    constraint fk_totipo_curso_id foreign key(curso_id) references curso(id)

);


create table resposta(

    id bigint not null auto_increment,
    mensagem varchar(255) not null,
    topico_id bigint not null,
    data_criacao datetime not null,
    autor_id bigint not null,
    solucao tinyint(1) not null,

    primary key(id),
    constraint fk_pesposta_topico_id foreign key(topico_id) references topico(id),
     constraint fk_resposta_autor_id foreign key(autor_id) references usuario(id)

);

