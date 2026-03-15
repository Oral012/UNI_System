package uni.system.model;

import java.util.ArrayList;

public class Admin extends User {
    
    public Admin ( String name, String email, String password, String role, String status ){
        super(name, email, password, role, status);
    }
    
    public String getRole ( ) {
        return "Admin";
    }

    public void addCourse(ArrayList<Course> coursesTaught, Course course) {
        coursesTaught.add(course);
    }
    public void addStudent(ArrayList<Student> students, Student student) {
        students.add(student);
    }
    public void removeCourse(ArrayList<Course> coursesTaught, String courseId){
        for (Course c : coursesTaught) {
            if (c.getCourseId().equals(courseId)) {
                coursesTaught.remove(c);
                break;
            }
        }
    }  

    public void viewAllCourses(ArrayList<Course> coursesTaught) {
        for (Course c : coursesTaught) {
            System.out.println(c);
        }
    }
    
}
