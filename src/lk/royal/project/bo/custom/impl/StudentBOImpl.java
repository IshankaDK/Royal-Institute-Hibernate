package lk.royal.project.bo.custom.impl;


import lk.royal.project.bo.custom.StudentBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.impl.StudentDAOImpl;
import lk.royal.project.dto.StudentDTO;

import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAOImpl studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    @Override
    public boolean addStudent(StudentDTO customer) throws Exception {
        return false;
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO customer) throws Exception {
        return false;
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudent() throws Exception {
        return null;
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
