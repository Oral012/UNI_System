package uni.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import uni.system.model.User;
import uni.system.action.*;
import uni.system.model.Admin;
import uni.system.model.Course;
import uni.system.model.Department;
import uni.system.model.Enrollment;
import uni.system.model.Lecturer;
import uni.system.model.Role;
import uni.system.model.Student;
import uni.system.model.University;

public class App 
{
    public static void main( String[] args )
    {
      //University part
      University university = new University( "CADT", "Phnom Penh");
      // Department part
      Department CS = new Department( "COMPUTER SCIENCE", "CS");
      Department TN = new Department ( "TELECOM AND NETWORK", "TN");
      Department DB = new Department( "DIGITAL BUSINESS", "DB");
      university.addDepartment(DB);
      university.addDepartment(CS);
      university.addDepartment(TN);
      
      //student part
      Student sora = new Student("sora", "sora@123", "12345", "S001", CS, "SE",2);
      Student sori = new Student("sori", "sori@123", "12345", "S002", CS, "SE",1);
      Student s3 = new Student("sore", "sore@123", "12345", "S003", CS, "SE",3);
      Student s4 = new Student("devit", "devit@123", "234", "S004", TN, "IT", 2);
      Student s5 = new Student("saki", "saki@123", "12345", "S005", DB, "DB", 1);
      university.addUser(sora);
      university.addUser(sori);
      university.addUser(s3);
      university.addUser(s4);
      university.addUser(s5);
      //Lecturer part
      
      Lecturer somnang = new Lecturer("somnang", "somnang@123", "123", DB, "L001", "DB");
      university.addUser(somnang); 

      university.addUser( new Lecturer("Sophea", "sophea.k@email.com", "pass456", CS, "L002", "Java"));
      university.addUser(new Lecturer("Rathana", "rathana.v@email.com", "secure789", CS, "L003", "Algorithms"));
      university.addUser(new Lecturer("Borey", "borey.s@email.com", "borey2026", TN, "L004", "Networking"));
      university.addUser( new Lecturer("Channary", "channary.m@email.com", "chan!321", CS, "L005", "Machine Learning"));
      //Admin part
      
      Admin a1 = new Admin( "admin", "admin@123", "admin", "A001");
      university.addUser(a1);

       //course part
      Course CA = new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 2);
      Course ITE = new Course("ITE", "INTERNET TECHNOLOGY", 3, TN, 1);
      Course SE = new Course("SE", "SOFTWARE ENGINEERING", 3, CS, 3);
      Course c4 = new Course("DB", "DATABASE", 3, DB, 1);
      Course c5 = new Course("DS", "DATA STRUCTURE", 3, CS, 2);
      
      university.printAllUsers();

      //Enroll to course
      Enrollment e1 = new Enrollment(sora, CA);
      Enrollment e2 = new Enrollment(sora, ITE);
      Enrollment e3 = new Enrollment(sora, SE);
      Enrollment e4 = new Enrollment(sori, CA);
      EnrollmentStudent enrollList = new EnrollmentStudent();
      enrollList.enrollStudent(e2);
      enrollList.enrollStudent(e3);
      enrollList.enrollStudent(e4);
      enrollList.enrollStudent(e1);
      enrollList.printEnrollment();


      //Assign course
      somnang.setAssignedCourses(new ArrayList<Course>(Arrays.asList(CA, ITE ,SE)));

      //Assign grade
      somnang.assignGrade(sora, CA, 50);
      a1.assignGrade(sora, CA, 70);
      a1.assignGrade(sora, ITE, 80);
      sora.viewGrade();
      somnang.viewCourseGrades(enrollList);

      

      Scanner scanner = new Scanner( System.in);
      System.out.println("Enter your name:");
      String name = scanner.next();
      System.out.println("Enter your password:");
      String password = scanner.next();
      //Authentication part
      User currentUser = university.login(name, password);
    }

    public void adminDashboard( Admin admin){

    }


    public void handleUserDashboard( User user){
      if ( user instanceof Admin){
        // show admin dashboard
        Admin admin = ( Admin ) user;
      } else if ( user instanceof Lecturer){
        // show lecturer dashboard
        Lecturer lecturer = ( Lecturer ) user;
      } else if ( user instanceof Student){
        // show student dashboard
        Student student = ( Student ) user;

      }
      }
    
    
}
