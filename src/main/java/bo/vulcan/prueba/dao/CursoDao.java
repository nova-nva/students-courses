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
public class CursoDao {
    private final DataSource dataSource;

    @Autowired
    public CursoDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Curso> getCurso(){
        String query = "select c.curso_id, c.nombre, c.cupo" +
                "       from curso c" +
                "       where c.tuple_status = 1;";
        List<Curso> result = new ArrayList<>();
        try(
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setCurso_id(rs.getInt("curso_id"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCupo(rs.getInt("cupo"));
                curso.setTotalAlumnos(getAlumnosInCurso(curso.getCurso_id()));
                curso.setTotalFemenino(getAlumnosFemenino(curso.getCurso_id()));
                curso.setTotalMasculino(curso.getTotalAlumnos() - curso.getTotalFemenino());
                curso.setCapacidad((double) curso.getTotalAlumnos() / curso.getCupo());
                result.add(curso);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int getAlumnosInCurso(Integer curso_id){
        String query = "select count(*) as total" +
                "       from curso c" +
                "       left join cursos_alumnos ca on c.curso_id = ca.curso_id" +
                "       left join alumno a on ca.alumno_id = a.alumno_id" +
                "       where ca.tuple_status = 1" +
                "       and c.curso_id = ?";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            preparedStatement.setInt(1, curso_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return rs.getInt("total");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getAlumnosFemenino(Integer curso_id){
        String query = "select count(*) as mujeres" +
                "       from curso c" +
                "       left join cursos_alumnos ca on c.curso_id = ca.curso_id" +
                "       left join alumno a on ca.alumno_id = a.alumno_id" +
                "       where ca.tuple_status = 1" +
                "       and c.curso_id = ?" +
                "       and a.genero_id = 2;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            preparedStatement.setInt(1, curso_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return rs.getInt("mujeres");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public Curso getCursoById(Integer curso_id){
        String query = "select c.curso_id, c.nombre, c.cupo" +
                "       from curso c" +
                "       where c.curso_id = ?" +
                "       and c.tuple_status = 1;";
        Curso curso = new Curso();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            preparedStatement.setInt(1, curso_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                curso.setCurso_id(rs.getInt("curso_id"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCupo(rs.getInt("cupo"));
            }
            else
                curso.setCurso_id(-1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return curso;
    }

    public Curso createCurso(Curso curso){
        String query = "insert into curso (nombre, cupo)" +
                "       values (?, ?);";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setString(1, curso.getNombre());
            pstmt.setInt(2, curso.getCupo());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int last_id = rs.getInt(1);
                return getCursoById(last_id);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        Curso fail = new Curso();
        fail.setCurso_id(-1);
        return fail;
    }

    public boolean updateCurso(Curso curso){
        String query = "update curso" +
                "       set nombre = ?, " +
                "       cupo = ?" +
                "       where curso_id = ?;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setString(1, curso.getNombre());
            pstmt.setInt(2, curso.getCupo());
            pstmt.setInt(3, curso.getCurso_id());
            pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteCurso(Integer curso_id){
        String query = "update curso" +
                "       set tuple_status = 0 " +
                "       where curso_id = ?;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ){
            pstmt.setInt(1, curso_id);
            pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
