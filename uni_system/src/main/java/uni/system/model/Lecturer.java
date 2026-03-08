package uni.system.model;

import java.util.ArrayList;

public class Lecturer  extends User{
    private Department department;
    private String lecturerId;
    private String specialization;
    private ArrayList<Course> assignedCourses;
    public Lecturer (String name, String email, String password, Department department, String lecturerId, String specialization){
        super(name, email, password);
        setDepartment(department);
        setLecturerId(lecturerId);
        setSpecialization(specialization);
        assignedCourses = new ArrayList<>();
    }
    // Getters methods
    public Department getDepartment()       {      return department;}
    public String getLecturerId()           {      return lecturerId;}
    public String getSpecialization()       {      return specialization;}
    public ArrayList<Course> getAssignedCourses()   {   return assignedCourses;}

    // Setters methods
    public void setDepartment(Department department) {
        if( department == null) throw new IllegalArgumentException("error assign department");
        this.department = department;
    }
    public void setLecturerId(String lecturerId) {
        if ( lecturerId.isBlank()) throw new IllegalArgumentException("Lecturer Id must not be blank.");
        this.lecturerId = lecturerId;
    }
    public void setSpecialization(String specialization) {
        if ( specialization.isBlank()) throw new IllegalArgumentException("Specialization must not be blank.");
        this.specialization = specialization;
    }
    public void setAssignedCourses(ArrayList<Course> assignedCourses) {
        if ( assignedCourses.isEmpty()) throw new IllegalArgumentException("Assigned courses list must not be empty.");
        this.assignedCourses = assignedCourses;
    }
    
    @Override
    public void viewProfile() {
        System.out.println("Lecturer Profile:");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + getDepartment().getDepartmentName());
        System.out.println("Lecturer ID: " + getLecturerId());
        System.out.println("Specialization: " + getSpecialization());
        System.out.println("Assigned Courses:");
    }
    public void viewAssignedCourses(){
        System.out.println("Assigned Courses for " + getName() + ":");
        for (Course course : assignedCourses) {
            System.out.println(" - " + course.getCourseName());
        }
    }
    
}
