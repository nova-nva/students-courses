package bo.vulcan.prueba.dto;

import java.util.Objects;

public class Curso_Alumnos {
    private int cursos_alumnos_id;
    private int curso_id;
    private String curso;
    private int alumno_id;
    private String alumno;

    public Curso_Alumnos(){

    }

    public int getCursos_alumnos_id() {
        return cursos_alumnos_id;
    }

    public void setCursos_alumnos_id(int cursos_alumnos_id) {
        this.cursos_alumnos_id = cursos_alumnos_id;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso_Alumnos that = (Curso_Alumnos) o;
        return cursos_alumnos_id == that.cursos_alumnos_id && curso_id == that.curso_id && alumno_id == that.alumno_id && Objects.equals(curso, that.curso) && Objects.equals(alumno, that.alumno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursos_alumnos_id, curso_id, curso, alumno_id, alumno);
    }

    @Override
    public String toString() {
        return "Curso_Alumnos{" +
                "cursos_alumnos_id=" + cursos_alumnos_id +
                ", curso_id=" + curso_id +
                ", alumno_id=" + alumno_id +
                '}';
    }
}
