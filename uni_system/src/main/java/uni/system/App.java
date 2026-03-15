package uni.system;

import uni.system.model.Course;
import uni.system.model.Department;
import uni.system.model.Student;
import uni.system.service.Enrollment;
public class App 
{
    public static void main( String[] args )
    {
      //department part
      Department CS = new Department( "COMPUTER SCIENCE", "SE");
      Department TN = new Department ( "TELECOM AND NETWORK", "TN");
      //student part
      Student s1 = new Student("sora", "sora@123", "12345", "S001", CS, "SE",2);
      Student s2 = new Student("sori", "sori@123", "12345", "S002", CS, "SE",1);
      Student s3 = new Student("sore", "sore@123", "12345", "S003", CS, "SE",3);

       //course part
      Course c1 = new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 2);
      Course c2 = new Course("ITE", "INTERNET TECHNOLOGY", 3, TN, 1);
      Course c3 = new Course("SE", "SOFTWARE ENGINEERING", 3, CS, 3);
     
      //enrollment part
      Enrollment e1 = new Enrollment( s1, c1, 1);
      Enrollment e2 = new Enrollment(s2, c2, 1);
      Enrollment e3 = new Enrollment(s3, c3, 2);
      
      s1.viewEnrolledCourses();

      
        

    }
}
