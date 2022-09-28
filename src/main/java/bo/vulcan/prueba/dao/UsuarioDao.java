package bo.vulcan.prueba.dao;

import bo.vulcan.prueba.dto.Alumno;
import bo.vulcan.prueba.dto.Usuario;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsuarioDao {
    private final DataSource dataSource;

    public UsuarioDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public boolean login(Usuario usuario){
        String query = "select u.usuario_id, u.email" +
                "       from usuario u" +
                "       where u.email = ?" +
                "       and u.password = ?" +
                "       and u.tuple_status = 1;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ){
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
