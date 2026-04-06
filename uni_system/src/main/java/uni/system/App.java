package uni.system;

import java.util.*;

import uni.system.io.UserFileIO;
import uni.system.model.*;
public class App 
{
    public static void main( String[] args )
    {
      

      University cadt = new University("CADT", "Plov90");
      // Department computerScience = new Department("Computer Science", "CS");
      // Department teleCom = new Department("Telecommunication", "TNE");
      // Department digitalBiz = new Department("Digital Business", "DB");
      // cadt.addDepartment(computerScience);
      // cadt.addDepartment(teleCom);
      // cadt.addDepartment(digitalBiz);
      // UserFileIO.loadUsers(cadt);
      insertData(cadt);
      dashboard(cadt);

      // Admin admin = new Admin("admin", "admin@gmail.com", "admin1234", "admin");
      // cadt.addUser(admin);
      
      // dashboard(cadt, admin);





      
    }
//UserFileIO.loadUsers(cadt);
      // Department part
      // Department CS = new Department( "COMPUTER SCIENCE", "CS");
      // Department TN = new Department ( "TELECOM AND NETWORK", "TN");
      // Department DB = new Department( "DIGITAL BUSINESS", "DB");
      // university.addDepartment(DB);
      // university.addDepartment(CS);
      // university.addDepartment(TN);
      
      // //student part
      // Student s1 = new Student("sora", "sora@123.com", "12345", "S001", CS, "SE",2);
      // Student s2 = new Student("sori", "sori@123.com", "12345", "S002", CS, "SE",1);
      // Student s3 = new Student("sore", "sore@123.com", "12345", "S003", CS, "SE",3);
      // Student s4 = new Student("devit", "devit@123.com", "1234", "S004", TN, "IT", 2);
      // Student s5 = new Student("saki", "saki@123.com", "12345", "S005", DB, "DB", 1);
      // university.addUser(s1);
      // university.addUser(s2);
      // university.addUser(s3);
      // university.addUser(s4);
      // university.addUser(s5);
      // //Lecturer part
      // Lecturer l1 = new Lecturer("Sopheara", "sophea.k@email.com", "pass456", CS, "L002", "Java");
      //   university.addUser(l1);
      // university.addUser( new Lecturer("somnang", "somnang@123.com", "123", DB, "L001", "DB"));
      // university.addUser( new Lecturer("Sophea", "sophea.k@email.com", "pass456", CS, "L002", "Java"));
      // university.addUser(new Lecturer("Rathana", "rathana.v@email.com", "secure789", CS, "L003", "Algorithms"));
      // university.addUser(new Lecturer("Borey", "borey.s@email.com", "borey2026", TN, "L004", "Networking"));
      // university.addUser( new Lecturer("Channary", "channary.m@email.com", "chan!321", CS, "L005", "Machine Learning"));
      // //Admin part
      // university.addUser( new Admin( "admin", "admin@email.com", "admin", "A001"));
      // university.addUser( new Manager( "kim", "kim@gmail.com", "12345",  "M001" ));

      //   // assign couurse to lectuer
      //   //course part
      //   ArrayList<Course> courses = new ArrayList<>();
      //   courses.add(new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 2)); 
      //   courses.add(new Course("ITE", "INTERNET TECHNOLOGY", 3, TN, 1) );
      //   courses.add ( new Course("SE", "SOFTWARE ENGINEERING", 3, CS, 3));
      //   courses.add( new Course( "AI", "ARTIFICIAL INTELLIGENCE", 3, CS, 4));
      //   courses.add(new Course("DB", "DATABASE", 3, DB, 1));
      //   courses.add(new Course("DS", "DATA STRUCTURE", 3, CS, 2));
        
      //   l1.assignCourse(courses.get(0));
      
    
  
  public static void dashboard(University university){
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
        }
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
            if ( currentUser != null) {

              // show dashboard based on role
              if ( currentUser instanceof Admin) {
                Admin admin = ( Admin ) currentUser;
                admin.adminDashboard(university);
              } else if ( currentUser.getRole() == Role.LECTURER) {
                // show lecturer dashboard
                Lecturer lecturer = ( Lecturer ) currentUser;
                lecturer.dashboard(scanner);
              } else if ( currentUser.getRole() == Role.STUDENT) {
                // show student dashboard
                Student student = ( Student ) currentUser;
                student.dashboard(university.getCourses());
              } else if ( currentUser.getRole() == Role.MANAGER) {
                Manager manager = ( Manager ) currentUser;
                manager.dashboard(university);
              }
               else {
                System.out.println("Unknown role. Access denied.");
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
    public static void insertData ( University university) {
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
      Lecturer l1 = new Lecturer("Sopheara", "sopheara@email.com", "pass456", CS, "L002", "Java");
        university.addUser(l1);
      university.addUser( new Lecturer("somnang", "somnang@123.com", "123", DB, "L001", "DB"));
      university.addUser( new Lecturer("Sophea", "sophea.k@email.com", "pass456", CS, "L002", "Java"));
      university.addUser(new Lecturer("Rathana", "rathana.v@email.com", "secure789", CS, "L003", "Algorithms"));
      university.addUser(new Lecturer("Borey", "borey.s@email.com", "borey2026", TN, "L004", "Networking"));
      university.addUser( new Lecturer("Channary", "channary.m@email.com", "chan!321", CS, "L005", "Machine Learning"));
      //Admin part
      university.addUser( new Admin( "admin", "admin@email.com", "admin", "A001"));
      university.addUser( new Manager( "kim", "kim@gmail.com", "12345",  "M001" ));

        // assign couurse to lectuer
        //course part
        
        
        
        university.addCourse(new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 2)); 
        university.addCourse(new Course("ITE", "INTERNET TECHNOLOGY", 3, TN, 1) );
        university.addCourse( new Course("SE", "SOFTWARE ENGINEERING", 3, CS, 3));
        university.addCourse( new Course( "AI", "ARTIFICIAL INTELLIGENCE", 3, CS, 4));
        university.addCourse(new Course("DB", "DATABASE", 3, DB, 1));
        university.addCourse(new Course("DS", "DATA STRUCTURE", 3, CS, 2));
    }
  }


    