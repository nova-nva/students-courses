CREATE DATABASE vulcan_test;
USE vulcan_test;

CREATE TABLE usuario (
    usuario_id int not null primary key auto_increment,
    email varchar(200),
    password varchar(200),
    last_udpate timestamp not null default current_timestamp on update current_timestamp,
    tuple_status bool not null default true
);

CREATE TABLE curso (
    curso_id int not null primary key auto_increment,
    nombre varchar(200),
    cupo int,
    last_udpate timestamp not null default current_timestamp on update current_timestamp,
    tuple_status bool not null default true
);

CREATE TABLE genero (
    genero_id int not null primary key auto_increment,
    descripcion varchar(100),
    last_udpate timestamp not null default current_timestamp on update current_timestamp,
    tuple_status bool not null default true
);

CREATE TABLE alumno (
    alumno_id int not null primary key auto_increment,
    genero_id int not null,
    nombre varchar(100) not null,
    edad int not null,
    foreign key (genero_id) references genero(genero_id),
    last_udpate timestamp not null default current_timestamp on update current_timestamp,
    tuple_status bool not null default true
);

CREATE TABLE cursos_alumnos(
    cursos_alumnos_id int not null primary key auto_increment,
    curso_id int not null,
    alumno_id int not null,
    foreign key (curso_id) references curso(curso_id),
    foreign key (alumno_id) references alumno(alumno_id),
    last_udpate timestamp not null default current_timestamp on update current_timestamp,
    tuple_status bool not null default true
);