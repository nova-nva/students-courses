package bo.vulcan.prueba.api;

import bo.vulcan.prueba.bl.CursoBL;
import bo.vulcan.prueba.dto.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoApi {
    CursoBL cursoBL;

    @Autowired
    public CursoApi(CursoBL cursoBL){
        this.cursoBL = cursoBL;
    }

    @GetMapping(value = "/curso", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> getCurso(){
        return cursoBL.getCurso();
    }

    @GetMapping(value = "/curso/{curso_id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAlumnoById(@PathVariable(name = "curso_id") Integer curso_id){
        Curso curso = cursoBL.getCursoById(curso_id);
        if(curso.getCurso_id() != -1)
            return new ResponseEntity<>(curso, HttpStatus.OK);
        return new ResponseEntity<>("Curso no encontrado", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> createCurso(@RequestBody Curso curso){
        Curso newCurso = cursoBL.createCurso(curso);
        if(newCurso.getCurso_id() != -1)
            return new ResponseEntity<>(newCurso, HttpStatus.OK);
        return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateCurso(@RequestBody Curso curso){
        if(cursoBL.updateCurso(curso))
            return new ResponseEntity<>("Curso actualizado", HttpStatus.OK);
        return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/curso/{curso_id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> deleteCurso(@PathVariable(name = "curso_id") Integer curso_id){
        if(cursoBL.deleteCurso(curso_id))
            return new ResponseEntity<>("Curso eliminado", HttpStatus.OK);
        return new ResponseEntity<>("Curso no encontrado", HttpStatus.NOT_FOUND);
    }
}
