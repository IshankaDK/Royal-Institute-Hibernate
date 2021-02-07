package lk.royal.project.bo.custom.impl;

import lk.royal.project.bo.custom.CourseBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.impl.CourseDAOImpl;
import lk.royal.project.dto.CourseDTO;
import lk.royal.project.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAOImpl courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);

    @Override
    public boolean addCourse(CourseDTO dto) throws Exception {
        boolean add = courseDAO.add(new Course(dto.getCode(), dto.getCourseName(), dto.getFee(), dto.getDuration()));
        System.out.println(add);
        return add;
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        return courseDAO.update(new Course(dto.getCode(),dto.getCourseName(),dto.getFee(),dto.getDuration()));
    }

    @Override
    public CourseDTO getCourse(String id) throws Exception {
        Course course = courseDAO.find(id);
        return new CourseDTO(course.getCode(),course.getCourseName(),course.getFee(),course.getDuration());
    }

    @Override
    public List<CourseDTO> getAllCourse() throws Exception {
        List<Course> all = courseDAO.findAll();
        List<CourseDTO> dtos = new ArrayList<>();
        for (Course course : all) {
            dtos.add(new CourseDTO(course.getCode(),course.getCourseName(),course.getFee(),course.getDuration()));
        }
        return dtos;
    }
}
