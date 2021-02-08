package lk.royal.project.bo.custom;

import lk.royal.project.bo.SuperBO;
import lk.royal.project.dto.StudentDTO;

import java.util.List;

public interface ReportBO extends SuperBO {
    List<StudentDTO> getCourseWise(String id) throws Exception;

}
