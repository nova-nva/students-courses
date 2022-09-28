package bo.vulcan.prueba.dto;

import java.util.Objects;

public class Curso {
    private int curso_id;
    private String nombre;
    private int cupo;

    private int totalAlumnos;
    private int totalMasculino;
    private int totalFemenino;
    private double capacidad;

    public Curso(){

    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

    public int getTotalMasculino() {
        return totalMasculino;
    }

    public void setTotalMasculino(int totalMasculino) {
        this.totalMasculino = totalMasculino;
    }

    public int getTotalFemenino() {
        return totalFemenino;
    }

    public void setTotalFemenino(int totalFemenino) {
        this.totalFemenino = totalFemenino;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return curso_id == curso.curso_id && cupo == curso.cupo && Objects.equals(nombre, curso.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso_id, nombre, cupo);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "curso_id=" + curso_id +
                ", nombre='" + nombre + '\'' +
                ", cupo=" + cupo +
                '}';
    }
}
