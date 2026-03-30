package uni.system.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uni.system.model.Enrollment;

public class  Student extends User {
    private Department department;
    private String major;
    private String studentId;
    private int yearLevel;
    private Map<String, Double> gradeBook = new HashMap<>();
    ArrayList<Enrollment> enrollment;
    public Student (String name, String email, String password, String studentId,  Department department, String major, int yearLevel){
        super(name, email, password, Role.STUDENT);    //use constructor of Parent class ( User class)
        setDepartment(department);
        setMajor(major);
        setYearLevel(yearLevel);
        setStudentId(studentId);
        enrollment = new ArrayList<>();
    }
    // Setters methods
    public void setDepartment(Department department) {
        if(department == null){
            throw new IllegalArgumentException("Department must not be null.");
        }
        this.department = department;
    }
    public void setYearLevel(int yearLevel) {
        if(yearLevel < 1 || yearLevel > 4){
            throw new IllegalArgumentException("Year level must be between 1 and 4.");
        }
        this.yearLevel = yearLevel;
    }
    public void setMajor(String major) {
        if(major.isBlank()){
            throw new IllegalArgumentException("major must not be blank.");
        }
        this.major = major;
    }
    public void setStudentId (String studentId){
        if( studentId.isBlank()) throw new IllegalArgumentException("Student Id must not be blank.");
        this.studentId = studentId;
    }

    // Getters methods
    public int getYearLevel(){
        return yearLevel;
    }
    public Department getDepartment(){
        return department;
    }
    public String getMajor() {
        return major;
    }
    public String getStudentId() {
        return studentId;
    }
    // methods
    @Override
    public void viewProfile(){
        System.out.println("Name = " + getName() + "\n" 
        + "Student ID = " + getStudentId() + "\n"
        + "Email = " + getEmail() + "\n"
        + "Department = " + getDepartment() + "\n"
        + "Major = " + getMajor() + "\n"
        + "Year Level = " + getYearLevel() + "\n"
        );
    }
    public void addEnrollment(Course course){
        if ( course == null){
            throw new IllegalArgumentException("course cannot be null.");
        }
        for( Enrollment e : enrollment){
            if( e.getCourse().getCourseId().equals(course.getCourseId()) ){
                throw new IllegalArgumentException("Already enrolled this course.");
            }
        }
        Enrollment e = new Enrollment( this, course);
        enrollment.add(e);
    }
    public void removeEnrollment(String courseId){
        if( courseId.isBlank()) throw new IllegalArgumentException("CourseId must not be blank.");

        Enrollment e = null;
        for( Enrollment enrollment : enrollment){
            if( enrollment.getCourse().getCourseId().equals(courseId) ){
                e = enrollment;
                break;
            }
        }
        if ( e == null){
            throw new IllegalArgumentException("Not enrolled this course.");
        }
        enrollment.remove(e);
    }
    
    public void viewEnrolledCourses(){
        System.out.println("---------------- Enrolled Courses ----------------" );
        if( enrollment.isEmpty()){
            System.out.println("No enrolled courses.");
        } else {
            for ( Enrollment e : enrollment){
                System.out.println( e.getCourse().getCourseId() + " - " + e.getCourse().getCourseName() );
            }
        }
    }
    public void viewAvailableCourses(ArrayList<Course> courses){
        System.out.println("---------------- Available Courses ----------------" );
        if( courses.isEmpty()){
            System.out.println("No available courses.");
        } else {
            for ( Course c : courses){
                if( c.getDepartment().getDepartmentCode().equals(this.getDepartment().getDepartmentCode()) ){
                System.out.println( c.getCourseId() + " - " + c.getCourseName() );
            }
        }
    }
    }
    double getGrade(Course course){
        return gradeBook.get(course.getCourseId()) == null ? -1 : gradeBook.get(course.getCourseId());
    }
    void setGrade(Course course, double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Error: Grade must be between 0 and 100.");
        }
        gradeBook.put(course.getCourseId(), grade);
    }
    

    public void viewGrade() {
    if (gradeBook == null || gradeBook.isEmpty()) {
        System.out.println("No grades assigned yet.");
        return;
    }
    System.out.println(getName() +" Grades : ");
    for (Map.Entry<String, Double> entry : gradeBook.entrySet()) {
        if (entry.getKey() != null && entry.getValue() != null) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
    
    

    
    
}
