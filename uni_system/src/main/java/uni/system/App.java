package uni.system;

import java.lang.reflect.Array;
import java.util.*;

import uni.system.io.UserFileIO;
import uni.system.model.*;
import uni.system.model.Course;
import uni.system.model.Student;
import uni.system.model.User;
public class App 
{
    public static void main( String[] args )
    {
      

      University CADT = new University("CADT", "Phom Penh");
      ArrayList<Course> courses = new ArrayList<>(); //not sure where to put
      insertData(CADT, courses);
      UserFileIO.loadUsers(CADT);
      dashboard(CADT, courses);


      
    }

  public static void dashboard(University university, ArrayList<Course> courses){
    
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
            intruction();
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
              } else if ( currentUser instanceof Lecturer) {
                // show lecturer dashboard
                Lecturer lecturer = ( Lecturer ) currentUser;
                lecturer.dashboard(scanner);
              } else if ( currentUser instanceof Student) {
                // show student dashboard
                Student student = ( Student ) currentUser;
                student.dashboard(courses);
              } else if ( currentUser instanceof Manager) {
                Manager manager = ( Manager ) currentUser;
                manager.dashboard(university, courses);
              } else {
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
  
  public static void insertData( University university, ArrayList<Course> courses) {
      // Department part
      Department CS = new Department( "COMPUTER SCIENCE", "CS");
      Department TN = new Department ( "TELECOM AND NETWORK", "TN");
      Department DB = new Department( "DIGITAL BUSINESS", "DB");
      university.addDepartment(DB);
      university.addDepartment(CS);
      university.addDepartment(TN);
      
     //student part
      // university.addUser(new Student("sora", "sora@123.com", "12345", "S001", CS, "SE",2));
      // university.addUser(new Student("sori", "sori@123.com", "12345", "S002", CS, "SE",1));
      // university.addUser(new Student("sore", "sore@123.com", "12345", "S003", CS, "SE",3));
      // university.addUser(new Student("devit", "devit@123.com", "1234", "S004", TN, "IT", 2));
      // university.addUser(new Student("saki", "saki@123.com", "12345", "S005", DB, "DB", 1));
      // //Lecturer part
      // university.addUser( new Lecturer("somnang", "somnang@123.com", "12345", DB, "L001", "DB"));
      // university.addUser( new Lecturer("Sophea", "sophea.k@email.com", "pass456", CS, "L002", "Java"));
      // university.addUser(new Lecturer("Rathana", "rathana.v@email.com", "secure789", CS, "L003", "Algorithms"));
      // university.addUser(new Lecturer("Borey", "borey.s@email.com", "borey2026", TN, "L004", "Networking"));
      // university.addUser( new Lecturer("Channary", "channary.m@email.com", "chan!321", CS, "L005", "Machine Learning"));
      //Admin part
      university.addUser( new Admin( "admin", "admin@email.com", "admin", "A001"));
      //Manager part
      university.addUser( new Manager( "kim", "kim@gmail.com", "12345",  "M001" ));

       //course part
      courses.add(new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 1)); 
      courses.add(new Course("ITE", "INTERNET TECHNOLOGY", 3, TN, 1) );
      courses.add ( new Course("SE", "SOFTWARE ENGINEERING", 3, CS, 3));
      courses.add( new Course( "AI", "ARTIFICIAL INTELLIGENCE", 3, CS, 4));
      courses.add(new Course("DB", "DATABASE", 3, DB, 1));
      courses.add(new Course("DS", "DATA STRUCTURE", 3, CS, 2));
  }

    public static void intruction() {
      System.out.println("Name: hubert, pass:12345");
      System.out.println("Lecturer: gertrude, pass: 12345");
      System.out.println("Manager: josh, pass:12345");
      System.out.println("Admin: admin, pass: admin");
    }
  }

    