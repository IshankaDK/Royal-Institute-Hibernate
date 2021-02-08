package lk.royal.project.dao;

import lk.royal.project.entity.Student;
import lk.royal.project.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getCourseWiseStudent(String id) throws Exception;
}
