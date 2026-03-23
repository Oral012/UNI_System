package uni.system.model;

// import uni.system.class.Student;
// import uni.system.Course;

public class Enrollment {
    public enum EnrollmentStatus {
        ENROLLED,
        DROPPED,
        COMPLETED
    }

    private Student student;
    private Course course;
    private String semester;
    private int enrollYear;
    private EnrollmentStatus status;

    public Enrollment(Student student, Course course, String semester, int enrollYear) {
        this.student = student;
        this.course = course;
        setSemester(semester);
        setEnrollYear(enrollYear);
        this.status = EnrollmentStatus.ENROLLED;
    }
    public Student getStudent() {
        return student;
    }
    public EnrollmentStatus getStatus() {
        return status;
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
    @Override
    public String toString() {
        return   student.getName() + ", Course: " + course.getCourseName() + ", Semester: " + semester + ", Year: " + (2026 - enrollYear) + ", Status: " + getStatus();
    }

    public void dropCourse(){
        this.status = EnrollmentStatus.DROPPED;
    }
    public void completedCourse(){
        this.status = EnrollmentStatus.COMPLETED;
    }
    public boolean isActive(){
        return status == EnrollmentStatus.ENROLLED;
    }


    }

    
    
    

