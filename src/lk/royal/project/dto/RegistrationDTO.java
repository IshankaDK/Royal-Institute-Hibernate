package lk.royal.project.dto;

public class RegistrationDTO {
    private String regId;
    private String redDate;
    private double regFee;
    private StudentDTO studentDTO;
    private CourseDTO courseDTO;


    public RegistrationDTO() {
    }

    public RegistrationDTO(String regId, String redDate, double regFee, StudentDTO studentDTO, CourseDTO courseDTO) {
        this.setRegId(regId);
        this.setRedDate(redDate);
        this.setRegFee(regFee);
        this.setStudentDTO(studentDTO);
        this.setCourseDTO(courseDTO);
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRedDate() {
        return redDate;
    }

    public void setRedDate(String redDate) {
        this.redDate = redDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "regId='" + regId + '\'' +
                ", redDate='" + redDate + '\'' +
                ", regFee=" + regFee +
                ", studentDTO=" + studentDTO +
                ", courseDTO=" + courseDTO +
                '}';
    }
}
