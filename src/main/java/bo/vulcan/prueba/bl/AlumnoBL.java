package bo.vulcan.prueba.bl;

import bo.vulcan.prueba.dao.AlumnoDao;
import bo.vulcan.prueba.dto.Alumno;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlumnoBL {
    private final AlumnoDao alumnoDao;

    public AlumnoBL(AlumnoDao alumnoDao){
        this.alumnoDao = alumnoDao;
    }

    public List<Alumno> getAlumno(){
        return alumnoDao.getAlumno();
    }


    public Alumno getAlumnoById(Integer alumno_id){
        return alumnoDao.getAlumnoById(alumno_id);
    }

    public Alumno createAlumno(Alumno alumno){
        return alumnoDao.createAlumno(alumno);
    }

    public boolean updateAlumno(Alumno alumno){
        return alumnoDao.updateAlumno(alumno);
    }

    public boolean deleteAlumno(Integer alumno_id){
        return alumnoDao.deleteAlumno(alumno_id);
    }
}
