package uni.system;

import uni.system.action.EnrollmentStudent;
import uni.system.model.Course;
import uni.system.model.Enrollment;
import uni.system.model.Student;

public class App 
{
    public static void main( String[] args )
    {
      Student s1 = new Student("sora", "sora@123", "12345", "Student" , "ACTIVE", "COMPUTER SCIENCE", 3.0);
      Course c1 = new Course("CA", "COMPUTER ARCHITECTURE", 3);
      Student s2 = new Student("sori", "sori@123", "12345", "Student", "ACTIVE", "COMPUTER SCIENCE", 3.0);
      Course c2 = new Course("ITE", "COMPUTER ARCHITECTURE", 3);
      Student s3 = new Student("sore", "sore@123", "12345", "Student", "ACTIVE", "COMPUTER SCIENCE", 3.0);
      Course c3 = new Course("SE", "COMPUTER ARCHITECTURE", 3);
      Enrollment classes = new Enrollment(s1, c1, "Y2T1", 2024);
      Enrollment class1 = new Enrollment(s2, c2, "Y1T3", 2022);
      Enrollment class2 = new Enrollment(s3, c3, "Y3T1", 2023);
      EnrollmentStudent enrollment = new EnrollmentStudent(classes);
      enrollment.addStudent(class1);
      enrollment.addStudent(class2);
      enrollment.printEnrollment();
      System.out.println("-----------------------------");
      enrollment.printIndividual(1);//For specific information
        // s1.printInfo();
    
        

    }
}
