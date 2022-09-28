package bo.vulcan.prueba.bl;

import bo.vulcan.prueba.dao.CursoDao;
import bo.vulcan.prueba.dto.Curso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoBL {
    private final CursoDao cursoDao;

    public CursoBL(CursoDao cursoDao){
        this.cursoDao = cursoDao;
    }

    public List<Curso> getCurso(){
        return cursoDao.getCurso();
    }

    public Curso getCursoById(Integer curso_id){
        return cursoDao.getCursoById(curso_id);
    }

    public Curso createCurso(Curso curso){
        return cursoDao.createCurso(curso);
    }

    public boolean updateCurso(Curso curso){
        return cursoDao.updateCurso(curso);
    }

    public boolean deleteCurso(Integer curso_id){
        return cursoDao.deleteCurso(curso_id);
    }
}
