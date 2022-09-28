insert into genero(descripcion) values ('masculino'), ('femenino');
insert into alumno(genero_id, nombre, edad) values
(1, 'Alexander Sosa', 21),
(1, 'Jose Peralta', 18),
(2, 'Mayra Ramos', 29),
(2, 'Lorena Manzano', 25),
(1, 'Ben Rodriguez', 30),
(2, 'Ashley Romay', 20);
insert into curso(nombre, cupo) values ('Programacion 1', 10), ('Derecho', 10);
insert into cursos_alumnos(alumno_id, curso_id) values (1, 1), (5, 1), (6, 1), (2, 2), (3, 2), (4, 2);
insert into usuario(email, password) values ('root@noreply.com', 'root'), ('alexander@noreply.com', 'alex');