package uni.system;

import java.util.*;
import uni.system.model.User;
import uni.system.model.Admin;
import uni.system.model.Course;
import uni.system.model.Department;
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
      Student s1 = new Student("sora", "sora@123.com", "12345", "S001", CS, "SE",2);
      Student s2 = new Student("sori", "sori@123.com", "12345", "S002", CS, "SE",1);
      Student s3 = new Student("sore", "sore@123.com", "12345", "S003", CS, "SE",3);
      Student s4 = new Student("devit", "devit@123.com", "1234", "S004", TN, "IT", 2);
      Student s5 = new Student("saki", "saki@123.com", "12345", "S005", DB, "DB", 1);
      university.addUser(s1);
      university.addUser(s2);
      university.addUser(s3);
      university.addUser(s4);
      university.addUser(s5);
      //Lecturer part
      Lecturer l1 = new Lecturer("Sopheara", "sophea.k@email.com", "pass456", CS, "L002", "Java");
        university.addUser(l1);
      university.addUser( new Lecturer("somnang", "somnang@123.com", "123", DB, "L001", "DB"));
      university.addUser( new Lecturer("Sophea", "sophea.k@email.com", "pass456", CS, "L002", "Java"));
      university.addUser(new Lecturer("Rathana", "rathana.v@email.com", "secure789", CS, "L003", "Algorithms"));
      university.addUser(new Lecturer("Borey", "borey.s@email.com", "borey2026", TN, "L004", "Networking"));
      university.addUser( new Lecturer("Channary", "channary.m@email.com", "chan!321", CS, "L005", "Machine Learning"));
      //Admin part
      university.addUser( new Admin( "admin", "admin@email.com", "admin", "A001"));

        // assign couurse to lectuer
        //course part
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 2)); 
        courses.add(new Course("ITE", "INTERNET TECHNOLOGY", 3, TN, 1) );
        courses.add ( new Course("SE", "SOFTWARE ENGINEERING", 3, CS, 3));
        courses.add( new Course( "AI", "ARTIFICIAL INTELLIGENCE", 3, CS, 4));
        courses.add(new Course("DB", "DATABASE", 3, DB, 1));
        courses.add(new Course("DS", "DATA STRUCTURE", 3, CS, 2));
        
        l1.addAssignedCourse(courses.get(0));
      
      Scanner scanner = new Scanner( System.in);
      int choice = 0;
      do { 
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("Enter your choice: ");
        try {
          choice = scanner.nextInt();
        } catch ( InputMismatchException e){
          System.out.println("Invalid input. Please enter a number.");
          scanner.nextLine();
          continue;
        } catch ( IllegalStateException e) {
          System.out.println("Scanner is closed. Exiting the system.");
        return;
        }
        catch ( NoSuchElementException e) {
            System.out.println("Thanks for using the system.");
            return;

        }  // still figure out which exception that we need to catch
        
        intruction(); // provide info for testing the system
        switch (choice) {
          case 1:
            String name = "";
            String password = "";
           try {
              System.out.println("Enter your name:");
              name = scanner.next();
              System.out.println("Enter your password:");
              password = scanner.next();
            } catch ( InputMismatchException e){
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
            continue;
           } catch ( NoSuchElementException e) {
            System.out.println("Thanks for using the system.");
             return;
           }
            //Authentication part
            User currentUser = university.login(name, password);
            if ( currentUser != null){

              // // show dashboard based on role
              // if ( currentUser.getRole() == Role.ADMIN){
              //   Admin admin = ( Admin ) currentUser;
              //   adminDashboard(admin);
               if ( currentUser.getRole() == Role.LECTURER){
                // show lecturer dashboard
                Lecturer lecturer = ( Lecturer ) currentUser;
                lecturer.dashboard(lecturer, scanner);
              } else 
                if ( currentUser.getRole() == Role.STUDENT){
                // show student dashboard
                Student student = ( Student ) currentUser;
                student.dashboard(student, courses);
              }
            } else {
              System.out.println("Login failed! Invalid name or password.");
            }
            break;
          case 2:
            System.out.println("Exiting the system. Have a nice day!");
            break;
          default:
            System.out.println("Invalid choice. Please try again.");
            break;
        }
        
      } while ( choice != 2);
      scanner.close();
    }
    
    public static void intruction() {
      System.out.println("Name: sora, pass:12345");
      System.out.println("Lecturer: sopheara, pass: pass456");
      System.out.println("Admin: admin, pass: admin");
    }
  }

