-- students courses
select c.curso_id, c.nombre, c.cupo
from curso c
left join cursos_alumnos ca on c.curso_id = ca.curso_id
left join alumno a on ca.alumno_id = a.alumno_id
where ca.tuple_status = 1
and a.alumno_id = 1;

-- number students in course
select count(*) as total
from curso c
left join cursos_alumnos ca on c.curso_id = ca.curso_id
left join alumno a on ca.alumno_id = a.alumno_id
where ca.tuple_status = 1
and c.curso_id = 1;

-- number of woman students in course
select count(*) as mujeres
from curso c
left join cursos_alumnos ca on c.curso_id = ca.curso_id
left join alumno a on ca.alumno_id = a.alumno_id
where ca.tuple_status = 1
and c.curso_id = 2
and a.genero_id = 2;