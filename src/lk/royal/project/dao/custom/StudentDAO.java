package lk.royal.project.dao.custom;

import lk.royal.project.dao.CrudDAO;
import lk.royal.project.dao.SuperDAO;
import lk.royal.project.entity.Student;

public interface StudentDAO extends CrudDAO<Student, String> {
    public String getLastStudentId()throws Exception;
}
