package bo.vulcan.prueba.dto;

import java.util.List;
import java.util.Objects;

public class Alumno {
    private int alumno_id;
    private int genero_id;
    private String descripcion;
    private String nombre;
    private int edad;

    private List<Curso> cursos;

    public Alumno(){

    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public int getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return alumno_id == alumno.alumno_id && genero_id == alumno.genero_id && edad == alumno.edad && Objects.equals(descripcion, alumno.descripcion) && Objects.equals(nombre, alumno.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno_id, genero_id, descripcion, nombre, edad);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "alumno_id=" + alumno_id +
                ", genero_id=" + genero_id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
