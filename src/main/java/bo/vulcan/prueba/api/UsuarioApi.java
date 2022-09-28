package bo.vulcan.prueba.api;

import bo.vulcan.prueba.bl.UsuarioBL;
import bo.vulcan.prueba.dto.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioApi {
    UsuarioBL usuarioBL;

    @Autowired
    public UsuarioApi(UsuarioBL usuarioBL){
        this.usuarioBL = usuarioBL;
    }

    @PostMapping(value = "/usuario/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> login(@RequestBody Usuario usuario){
        if(usuarioBL.login(usuario))
            return new ResponseEntity<>("Bienvenido", HttpStatus.OK);
        return new ResponseEntity<>("Datos incorrectos", HttpStatus.BAD_REQUEST);
    }
}
