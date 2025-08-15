create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensage text not null,
    fecha_creacion datetime not null,
    status tinyint,
    autor_id bigint not null,
    curso_id bigint not null,
    primary key(id)
);

create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null,
    contrasena varchar(100) not null,
    primary key(id)
);

create table cursos(id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,
    primary key(id)
);

create table respuestas(
    id bigint not null auto_increment,
    mensage text not null,
    fecha_creacion datetime not null,
    topico_id bigint not null,
    autor_id bigint not null,
    primary key(id)
);

update topicos set status = 1;
