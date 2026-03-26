package uni.system.model;
import java.util.ArrayList;
import java.util.Scanner;

import uni.system.model.Enrollment;

public class  Student extends User {
    private Department department;
    private String major;
    private String studentId;
    private int yearLevel;
    ArrayList<Enrollment> enrollment;
    public Student (String name, String email, String password, String studentId,  Department department, String major, int yearLevel){
        super( name, email, password, Role.STUDENT);    //use constructor of Parent class ( User class)
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
        System.out.println("----------------------------------Student Profile----------------------------------");
        System.out.println("Name = " + getName() + "\n" 
        + "Student ID = " + getStudentId() + "\n"
        + "Email = " + getEmail() + "\n"
        + "Department = " + getDepartment() + "\n"
        + "Major = " + getMajor() + "\n"
        + "Year Level = " + getYearLevel() + "\n"
        );
        System.out.println("----------------------------------------------------------------------------------");
    }
    public void addEnrollment(String courseId, ArrayList<Course> courses){
        if ( enrollment.size() > 7){
            System.out.println("You can not enroll more than 7 courses.");
            return;
        }

        if ( courseId.isBlank()) throw new IllegalArgumentException("CourseId must not be blank.");
        Course course = null;
        for (Course c : courses) {
            if ( c.getCourseId().equalsIgnoreCase(courseId)  &&
             c.getDepartment().getDepartmentCode().equalsIgnoreCase(this.getDepartment().getDepartmentCode())
             && c.getYearLevel() == this.getYearLevel() ){
                course = c;
                break;
            }
        }
        if ( course == null) { System.out.println("Course not found or not available for your department and year level."); }
        Enrollment e = new Enrollment( this, course);
        enrollment.add(e);
    }
    public void removeEnrollment(String courseId){
        if ( enrollment.isEmpty()) {
            System.out.println("You haven't enrolled any courses yet.");
        }
        if( courseId.isBlank()) throw new IllegalArgumentException("CourseId must not be blank.");

        Enrollment e = null;
        for( Enrollment enrollment : enrollment){
            if( enrollment.getCourse().getCourseId().equalsIgnoreCase(courseId) ){
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
        System.out.println("--------------------------------------------------");
    }
    public void viewAvailableCourses(ArrayList<Course> courses){
        System.out.println("---------------- Available Courses ----------------" );
        if( courses.isEmpty()){
            System.out.println("No available courses.");
        } else {
            for ( Course c : courses){
                if( c.getDepartment().getDepartmentCode().equals(this.getDepartment().getDepartmentCode()) 
                && c.getYearLevel() == this.getYearLevel() ){
                System.out.println( c.getCourseId() + " - " + c.getCourseName() );
            }
        }
    }
        System.out.println("--------------------------------------------------");
    
    }
    public void studentDashboard( Student student, ArrayList<Course> courses){
        // Accept input
        Scanner scanner = new Scanner( System.in);
        do {
            System.out.println("1. View Profile");
            System.out.println("2. View Courses");
            System.out.println("3. View Available Courses");
            System.out.println("4. Add New Enrollment");
            System.out.println("5. Drop Course");
            System.out.println("6. Logout");
            System.out.println("Enter your choice: ");

      int choice = scanner.nextInt();
      switch ( choice){
        case 1:
          student.viewProfile(); break;
        case 2: 
          student.viewEnrolledCourses(); break;
        case 3: 
          student.viewAvailableCourses(courses); break;
        case 4:
            System.out.println("Enter course ID to enroll: ");
            String courseId = scanner.next();
            student.addEnrollment(courseId, courses); break;
        case 5:
            System.out.println("Enter course ID to drop: ");
            String courseIdToDrop = scanner.next();
            student.removeEnrollment(courseIdToDrop); break;
        case 6:
            scanner.close();
            return;
        default: 
            System.out.println("Invalid choice. Please try again.");
      }
      
    } while( true);
    }
    
    
}
