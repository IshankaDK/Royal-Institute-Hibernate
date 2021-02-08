package lk.royal.project.bo.custom.impl;

import lk.royal.project.bo.custom.ReportBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.QueryDAO;
import lk.royal.project.dao.custom.impl.QueryDAOImpl;
import lk.royal.project.dao.custom.impl.StudentDAOImpl;
import lk.royal.project.dto.StudentDTO;
import lk.royal.project.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReportBOImpl implements ReportBO {

    QueryDAOImpl queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
    @Override
    public List<StudentDTO> getCourseWise(String id) throws Exception {
        List<Student> list= queryDAO.getCourseWiseStudent(id);
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : list) {
            dtos.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return dtos;
    }
}
