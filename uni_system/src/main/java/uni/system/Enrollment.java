package uni.system;

public class Enrollment {
    private Student student;
    private Course course;
    private String semester;
    private int enrollYear;
    private String status;
    public Enrollment(Student student, Course course, String semester, int enrollYear, String status) {
        this.student = student;
        this.course = course;
        setSemester(semester);
        setEnrollYear(enrollYear);
        this.status = "Active";
    }
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public String getSemester() {
        return semester;
    }
    public int getEnrollYear() {
        return enrollYear;
    }
    public String getStatus() {
        return status;
    }
    public void setEnrollYear(int enrollYear ){
        if(enrollYear <2000){
            throw new IllegalArgumentException("Can not accept year.!");
        }
        this.enrollYear = enrollYear;
    }
    public  void setSemester(String semester){
        if(semester.isBlank()){
            throw new IllegalArgumentException("semester data can not be blank.");
        }
        this.semester = semester;
    }
    
}
