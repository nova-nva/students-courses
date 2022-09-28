package bo.vulcan.prueba.dao;

import bo.vulcan.prueba.dto.Alumno;
import bo.vulcan.prueba.dto.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlumnoDao {
    private final DataSource dataSource;

    @Autowired
    public AlumnoDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Alumno> getAlumno(){
        String query = "select a.alumno_id, g.genero_id, g.descripcion, a.nombre, a.edad" +
                "       from alumno a" +
                "       left join genero g on a.genero_id = g.genero_id" +
                "       where a.tuple_status = 1;";
        List<Alumno> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Alumno alumno = new Alumno();
                alumno.setAlumno_id(rs.getInt("alumno_id"));
                alumno.setGenero_id(rs.getInt("genero_id"));
                alumno.setDescripcion(rs.getString("descripcion"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setEdad(rs.getInt("edad"));
                alumno.setCursos(getCursosFromAlumno(alumno.getAlumno_id()));
                result.add(alumno);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Curso> getCursosFromAlumno(Integer alumno_id){
        String query = "select c.curso_id, c.nombre, c.cupo" +
                "       from curso c" +
                "       left join cursos_alumnos ca on c.curso_id = ca.curso_id" +
                "       left join alumno a on ca.alumno_id = a.alumno_id" +
                "       where ca.tuple_status = 1" +
                "       and a.alumno_id = ?";
        List<Curso> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            preparedStatement.setInt(1, alumno_id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setCurso_id(rs.getInt("curso_id"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCupo(rs.getInt("cupo"));
                result.add(curso);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public Alumno getAlumnoById(Integer alumno_id){
        String query = "select a.alumno_id, g.descripcion, g.genero_id,  a.nombre, a.edad" +
                "       from alumno a" +
                "       left join genero g on a.genero_id = g.genero_id" +
                "       where a.alumno_id = ?" +
                "       and a.tuple_status = 1;";
        Alumno alumno = new Alumno();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            System.out.println("buscando alumno con id: " + alumno_id);
            preparedStatement.setInt(1, alumno_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                System.out.println("si se encontro");
                alumno.setAlumno_id(rs.getInt("alumno_id"));
                alumno.setGenero_id(rs.getInt("genero_id"));
                alumno.setDescripcion(rs.getString("descripcion"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setEdad(rs.getInt("edad"));
                alumno.setCursos(getCursosFromAlumno(alumno.getAlumno_id()));
            }
            else
                alumno.setAlumno_id(-1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return alumno;
    }

    public Alumno createAlumno(Alumno alumno){
        String query = "insert into alumno (genero_id, nombre, edad)" +
                "       values (?, ?, ?);";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setInt(1, alumno.getGenero_id());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setInt(3, alumno.getEdad());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int last_id = rs.getInt(1);
                return getAlumnoById(last_id);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        Alumno fail = new Alumno();
        fail.setAlumno_id(-1);
        return fail;
    }

    public boolean updateAlumno(Alumno alumno){
        String query = "update alumno" +
                "       set genero_id = ?, " +
                "       nombre = ?, " +
                "       edad = ?" +
                "       where alumno_id = ?;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, alumno.getGenero_id());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setInt(3, alumno.getEdad());
            pstmt.setInt(4, alumno.getAlumno_id());
            pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteAlumno(Integer alumno_id){
        String query = "update alumno" +
                "       set tuple_status = 0 " +
                "       where alumno_id = ?;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ){
            pstmt.setInt(1, alumno_id);
            pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
