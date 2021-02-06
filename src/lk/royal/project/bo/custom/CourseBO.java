package lk.royal.project.bo.custom;

import lk.royal.project.bo.SuperBO;
import lk.royal.project.dto.CourseDTO;
import lk.royal.project.dto.StudentDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    public boolean addCourse(CourseDTO dto) throws Exception;

    public boolean deleteCourse(String id) throws Exception;

    public boolean updateCourse(CourseDTO dto) throws Exception;

    public CourseDTO getCourse(String id) throws Exception;

    public List<CourseDTO> getAllCourse() throws Exception;

}
