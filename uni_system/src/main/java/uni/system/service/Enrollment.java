package uni.system.service;

import uni.system.model.Course;
import uni.system.model.Student;

// import uni.system.class.Student;
// import uni.system.Course;

public class Enrollment {
    private Student student;
    private Course course;
    private int term;
    public Enrollment(Student student, Course course, int term) {
        this.student = student;
        this.course = course;
        setTerm(term);
    }
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public int getTerm() {
        return term;
    }
    public  void setTerm(int term){
        if(term <=0 || term >4){
            throw new IllegalArgumentException("semester data can not be blank.");
        }
        this.term = term;
    }
    @Override
    public String toString() {
        return   student.getName() + ", Course: " + course.getCourseName() + ", Term: " + term + '\n';
    }
    }
    
    

