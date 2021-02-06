package lk.royal.project.model;

public class CourseTM {
    private String code;
    private String courseName;
    private double fee;
    private String duration;

    public CourseTM() {
    }

    public CourseTM(String code, String courseName, double fee, String duration) {
        this.setCode(code);
        this.setCourseName(courseName);
        this.setFee(fee);
        this.setDuration(duration);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "CourseTM{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", fee=" + fee +
                ", duration='" + duration + '\'' +
                '}';
    }
}
