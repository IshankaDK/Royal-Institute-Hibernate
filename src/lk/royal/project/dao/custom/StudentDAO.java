package lk.royal.project.dao.custom;

import lk.royal.project.dao.SuperDAO;
import lk.royal.project.entity.Student;

public interface StudentDAO extends SuperDAO<Student, String> {
    public String getLastStudentId()throws Exception;
}
