package bo.vulcan.prueba.api;

import bo.vulcan.prueba.bl.AlumnoBL;
import bo.vulcan.prueba.dto.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlumnoApi {
    AlumnoBL alumnoBL;

    @Autowired
    public AlumnoApi(AlumnoBL alumnoBL){
        this.alumnoBL = alumnoBL;
    }

    @GetMapping(value = "/alumno", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Alumno> getAlumno(){
        return alumnoBL.getAlumno();
    }

    @GetMapping(value = "/alumno/{alumno_id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAlumnoById(@PathVariable(name = "alumno_id") Integer alumno_id){
        Alumno alumno = alumnoBL.getAlumnoById(alumno_id);
        if(alumno.getAlumno_id() != -1)
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/alumno", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> createAlumno(@RequestBody Alumno alumno){
        Alumno newAlumno = alumnoBL.createAlumno(alumno);
        if(newAlumno.getAlumno_id() != -1)
            return new ResponseEntity<>(newAlumno, HttpStatus.OK);
        return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/alumno", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateAlumno(@RequestBody Alumno alumno){
        if(alumnoBL.updateAlumno(alumno))
            return new ResponseEntity<>("Alumno actualizado", HttpStatus.OK);
        return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/alumno/{alumno_id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> deleteAlumno(@PathVariable(name = "alumno_id") Integer alumno_id){
        if(alumnoBL.deleteAlumno(alumno_id))
            return new ResponseEntity<>("Alumno eliminado", HttpStatus.OK);
        return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
    }
}
