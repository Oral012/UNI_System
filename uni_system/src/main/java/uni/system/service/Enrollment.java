package uni.system.service;

import uni.system.model.Course;
import uni.system.model.Student;

// import uni.system.class.Student;
// import uni.system.Course;

public class Enrollment {
    private Student student;
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    @Override
    public String toString() {
        return   student.getName() + ", Course: " + course.getCourseName();
    }
    }
    
    

