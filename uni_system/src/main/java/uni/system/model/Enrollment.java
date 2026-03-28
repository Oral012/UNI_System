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
    private EnrollmentStatus status;


    public Enrollment (Student student, Course course){
        this.student = student;
        this.course = course;
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return course.getCourseId() + " - " + course.getCourseName() + " - Status: " + getStatus();
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

    
    
    

