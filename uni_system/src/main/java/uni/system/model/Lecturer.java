package uni.system.model;

import java.util.ArrayList;
import java.util.List;

import uni.system.action.EnrollmentStudent;
import uni.system.action.Gradeable;

public class Lecturer extends User implements Gradeable {

    private Department department;
    private String lecturerId;
    private String specialization;
    private ArrayList<Course> assignedCourses;
    public Lecturer (String name, String email, String password, Department department, String lecturerId, String specialization){
        super(name, email, password, Role.LECTURER);
        setDepartment(department);
        setLecturerId(lecturerId);
        setSpecialization(specialization);
        this.assignedCourses = new ArrayList<>();
    }

    public Department getDepartment() { 
        return department; 
    }
    
    public String getLecturerId() { 
        return lecturerId; 
    }
    
    public String getSpecialization() { 
        return specialization; 
    }
    
    public ArrayList<Course> getAssignedCourses() { 
        return assignedCourses; 
    }


    public void setDepartment(Department department) {
        if (department == null) throw new IllegalArgumentException("Department cannot be null.");
        this.department = department;
    }

    public void setLecturerId(String lecturerId) {
        if (lecturerId == null || lecturerId.isBlank()) throw new IllegalArgumentException("Lecturer ID must not be blank.");
        this.lecturerId = lecturerId;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) throw new IllegalArgumentException("Specialization must not be blank.");
        this.specialization = specialization;
    }

    public void setAssignedCourses(ArrayList<Course> assignedCourses) {
        if (assignedCourses == null) throw new IllegalArgumentException("Assigned courses list cannot be null.");
        this.assignedCourses = assignedCourses;
    }

    @Override
    public void viewProfile() {
        System.out.println("===== Lecturer Profile =====");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department.getDepartmentName());
        System.out.println("Lecturer ID: " + lecturerId);
        System.out.println("Specialization: " + specialization);

        viewAssignedCourses();
    }

    public void viewAssignedCourses() {
        System.out.println("Assigned Courses for " + getName() + ":");

        if (assignedCourses.isEmpty()) {
            System.out.println("No courses assigned.");
            return;
        }

        for (Course course : assignedCourses) {
            System.out.println(" - " + course);
        }
    }
    public void viewCourseGrades(EnrollmentStudent enrollList){
        System.out.println("Lecturer " + getName() + " courses: ");
        for (Course c : assignedCourses) {
            boolean hasStudents = false;
            for (Enrollment e : enrollList.getList()) {
                if (e.getCourse().getCourseId().equals(c.getCourseId())){
                    hasStudents = true;
            double grade = e.getStudent().getGrade(c);

            if (grade >= 0) {
                System.out.println("(" + c.getCourseId() + ") " + e.getStudent().getName() + " : " + grade);
            } else {
                System.out.println("(" + c.getCourseId() + ") " + e.getStudent().getName() + " : Not graded yet");
            }
        }
    }
        if (!hasStudents) System.out.println("  No students enrolled in this course.");
    }       
                
    }

    @Override
    public void assignGrade(Student student, Course course, double grade) {
        student.setGrade(course, grade); 
        System.out.println("Lecturer " + getName() + " recorded: " + student.getName() + " | " + course.getCourseId() + " | " + grade);
    }
        
    }