package uni.system;

import java.util.Scanner;
import uni.system.model.*;
import uni.system.action.EnrollmentStudent;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // ===== 1) Setup departments =====
            Department CS = new Department("COMPUTER SCIENCE", "CS");
            Department TN = new Department("TELECOM AND NETWORK", "TN");

            // ===== 2) Setup courses =====
            Course c1 = new Course("CA", "COMPUTER ARCHITECTURE", 3, CS, 2);
            Course c2 = new Course("DS", "DATA STRUCTURE", 3, CS, 2);
            Course c3 = new Course("NW", "NETWORK FUNDAMENTALS", 3, TN, 2);

            // ===== 3) Setup students =====
            Student s1 = new Student("Sora", "sora@123.com", "12345", "S001", CS, "SE", 2);
            Student s2 = new Student("Sori", "sori@123.com", "12345", "S002", CS, "SE", 1);
            Student s3 = new Student("Nina", "nina@123.com", "12345", "S003", TN, "TE", 1);

            // ===== 4) Enroll students in courses =====
            s1.addEnrollment(c1); // Sora -> Computer Architecture
            s1.addEnrollment(c2); // Sora -> Data Structure
            s2.addEnrollment(c2); // Sori -> Data Structure
            s3.addEnrollment(c3); // Nina -> Network Fundamentals

            // ===== 5) Setup lecturer =====
            Lecturer lecturer = new Lecturer("Sophea", "sophea.k@email.com", "pass456", CS, "L002", "Java");
            lecturer.addAssignedCourse(c1);
            lecturer.addAssignedCourse(c2);

            // ===== 6) Enrollment list =====
            EnrollmentStudent enrollList = new EnrollmentStudent();
            enrollList.enrollStudent(s1.getEnrollments());
            enrollList.enrollStudent(s2.getEnrollments());
            enrollList.enrollStudent(s3.getEnrollments());

            boolean exit = false;
            while (!exit) {
                System.out.println("\n===== MENU =====");
                System.out.println("1. View Profile");
                System.out.println("2. View My Courses");
                System.out.println("3. Assign Grade");
                System.out.println("4. View Course Grades");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                String choiceInput = scanner.nextLine();
                int choice = 0;

                try {
                    choice = Integer.parseInt(choiceInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Must be a number 1-5.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        lecturer.viewProfile();
                        break;
                    case 2:
                        lecturer.viewCourses();
                        break;
                    case 3:
                        try {
                            System.out.print("Enter student name: ");
                            String studentName = scanner.nextLine();

                            System.out.print("Enter course ID: ");
                            String courseId = scanner.nextLine();

                            System.out.print("Enter grade (0-100): ");
                            double grade = Double.parseDouble(scanner.nextLine());

                            // ===== Validate student =====
                            Student targetStudent = null;
                            if (s1.getName().equalsIgnoreCase(studentName)) {
                                targetStudent = s1;
                            } else if (s2.getName().equalsIgnoreCase(studentName)) {
                                targetStudent = s2;
                            } else if (s3.getName().equalsIgnoreCase(studentName)) {
                                targetStudent = s3;
                            }

                            if (targetStudent == null) {
                                throw new IllegalArgumentException("Student not found: " + studentName);
                            }

                            // ===== Validate course =====
                            Course targetCourse = null;
                            for (Course c : lecturer.getAssignedCourses()) {
                                if (c.getCourseId().equalsIgnoreCase(courseId)) {
                                    targetCourse = c;
                                    break;
                                }
                            }

                            if (targetCourse == null) {
                                throw new IllegalArgumentException("Course not assigned to lecturer: " + courseId);
                            }

                            // ===== Assign grade =====
                            lecturer.assignGrade(targetStudent, targetCourse, grade);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            lecturer.viewCourseGrades(enrollList);
                        } catch (Exception e) {
                            System.out.println("Error viewing grades: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Exiting Lecturer Test. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Must be 1-5.");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}