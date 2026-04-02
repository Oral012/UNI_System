package uni.system.model;

// import uni.system.class.Student;
// import uni.system.Course;

public class Enrollment {

    private Student student;
    private Course course;
    private int score;


    public Enrollment (Student student, Course course){
        this.student = student;
        this.course = course;
        this.score = -1;
    }
    public int getScore() {
        return score;
    }
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public void setScore( int score) {
        if ( score > 100 || score < 0 ) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        this.score = score;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return course.getCourseId() + " - " + course.getCourseName();
    }


    }

    
    
    

