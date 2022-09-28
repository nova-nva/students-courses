package bo.vulcan.prueba.bl;

import bo.vulcan.prueba.dao.UsuarioDao;
import bo.vulcan.prueba.dto.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBL {
    private final UsuarioDao usuarioDao;

    @Autowired
    public UsuarioBL(UsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    public boolean login(Usuario usuario){
        return usuarioDao.login(usuario);
    }
}
