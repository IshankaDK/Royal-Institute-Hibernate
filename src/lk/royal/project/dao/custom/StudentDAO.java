package lk.royal.dao.custom;

import lk.royal.entity.Student;

public interface StudentDAO extends SuperDAO<Student, String> {
    public String getLastStudentId()throws Exception;
}
