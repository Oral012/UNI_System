package uni.system.service;

import uni.system.model.Course;
import uni.system.model.Student;

// import uni.system.class.Student;
// import uni.system.Course;

public class Enrollment {
    private Student student;
    private Course course;
    private int term;
    private int yearLevel;
    public Enrollment(Student student, Course course, int term, int yearLevel) {
        this.student = student;
        this.course = course;
        setTerm(term);
        setYearLevel(yearLevel);
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
    public int getYearLevel() {
        return yearLevel;
    }
    public void setYearLevel(int enrollYear ){
        if(enrollYear <2000){
            throw new IllegalArgumentException("Can not accept year.!");
        }
        this.yearLevel = enrollYear;
    }
    public  void setTerm(int term){
        if(term <=0 || term >4){
            throw new IllegalArgumentException("semester data can not be blank.");
        }
        this.term = term;
    }
    @Override
    public String toString() {
        return   student.getName() + ", Course: " + course.getCourseName() + ", Term: " + term + ", Year: " + (2026 - yearLevel);
    }
    public void printInfo(){
        student.printInfo();
    }
    }
    
    

