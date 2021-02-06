package lk.royal.project.bo.custom;

import lk.royal.project.bo.SuperBO;
import lk.royal.project.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean addStudent(StudentDTO customer) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public boolean updateStudent(StudentDTO customer) throws Exception;

    public StudentDTO getStudent(String id) throws Exception;

    public List<StudentDTO> getAllStudent() throws Exception;

    public String newStudentId() throws Exception;


}
