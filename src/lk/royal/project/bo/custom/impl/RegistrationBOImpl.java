package lk.royal.project.bo.custom.impl;

import lk.royal.project.bo.custom.RegistrationBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.RegistrationDAO;
import lk.royal.project.dao.custom.impl.RegistrationDAOImpl;
import lk.royal.project.dto.CourseDTO;
import lk.royal.project.dto.RegistrationDTO;
import lk.royal.project.dto.StudentDTO;
import lk.royal.project.entity.Course;
import lk.royal.project.entity.Registration;
import lk.royal.project.entity.Student;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAOImpl dao = DAOFactory.getInstance().getDAO(DAOType.REGISTRATION);
    @Override
    public boolean addRegistration(RegistrationDTO dto) throws Exception {
        StudentDTO studentDTO = dto.getStudentDTO();
        Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender());
        CourseDTO courseDTO = dto.getCourseDTO();
        Course course = new Course(courseDTO.getCode(), courseDTO.getCourseName(), courseDTO.getFee(), courseDTO.getDuration());
        return dao.add(new Registration(dto.getRegId(),dto.getRedDate(),dto.getRegFee(),student,course));
    }

    @Override
    public RegistrationDTO getRegistration(String id) throws Exception {
        Registration reg = dao.find(id);
        return null;
    }

    @Override
    public String newRegID() throws Exception {
        String lastRegId = dao.getLastRegId();
        if(lastRegId == null){
            return "R001";
        }
        int newID = Integer.parseInt(lastRegId.substring(1,4))+1;

        if(newID < 10){
            return "R00"+newID;
        }else if (newID < 100){
            return "R0"+newID;
        }else {
            return "R"+newID;
        }
    }
}
