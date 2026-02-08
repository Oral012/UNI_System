package uni.system.model;

// import uni.system.class.Student;
// import uni.system.Course;

public class Enrollment {
    private Student student;
    private Course course;
    private String semester;
    private int enrollYear;
    public Enrollment(Student student, Course course, String semester, int enrollYear) {
        this.student = student;
        this.course = course;
        setSemester(semester);
        setEnrollYear(enrollYear);
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
        return   student.getName() + ", Course: " + course.getCourseName() + ", Semester: " + semester + ", Year: " + (2026 - enrollYear);
    }
    public void printInfo(){
        student.printInfo();
    }
    }
    
    

