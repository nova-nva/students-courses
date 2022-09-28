package bo.vulcan.prueba.dto;

import java.util.Objects;

public class Genero {
    private int genero_id;
    private String descripcion;

    public Genero(){

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return genero_id == genero.genero_id && Objects.equals(descripcion, genero.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genero_id, descripcion);
    }

    @Override
    public String toString() {
        return "Genero{" +
                "genero_id=" + genero_id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
