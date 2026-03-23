package uni.system.model;

import java.util.ArrayList;

public class Admin extends User  {
    public Admin ( String name, String email, String password, String adminId){
        super(name, email, password, Role.ADMIN);
    }

    
    public Role getRole ( ) {
        return  super.getRole();
    }
    
    @Override
    public void viewProfile() {
     // TODO Auto-generated method stub
     System.out.println("Name = " + getName() + "\n" 
        + "Email = " + getEmail() + "\n"
        + "Role = " + getRole() );         
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
