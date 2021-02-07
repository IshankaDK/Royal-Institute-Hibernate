package lk.royal.project.bo.custom.impl;


import lk.royal.project.bo.custom.StudentBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.impl.StudentDAOImpl;
import lk.royal.project.dto.StudentDTO;
import lk.royal.project.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAOImpl studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    @Override
    public boolean addStudent(StudentDTO student) throws Exception {
        return studentDAO.add(new Student(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {
        return studentDAO.update(new Student(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student student = studentDAO.find(id);
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender());
    }

    @Override
    public List<StudentDTO> getAllStudent() throws Exception {
        List<Student> all = studentDAO.findAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : all) {
            dtos.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return dtos;
    }

    @Override
    public String newStudentId() throws Exception {
        String lastStudentId = studentDAO.getLastStudentId();
        if(lastStudentId == null){
            return "S001";
        }
        int newID = Integer.parseInt(lastStudentId.substring(1,4))+1;

        if(newID < 10){
            return "S00"+newID;
        }else if (newID < 100){
            return "S0"+newID;
        }else {
            return "S"+newID;
        }
    }
}
