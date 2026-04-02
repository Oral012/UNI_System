package uni.system.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import uni.system.action.EnrollmentStudent;

public class Lecturer extends User {
    private Department department;
    private String lecturerId;
    private String specialization;
    private ArrayList<Course> assignedCourses;

    // Constructor
    public Lecturer(String name, String email, String password,
            Department department, String lecturerId, String specialization) {
        super(name, email, password, Role.LECTURER);
        setDepartment(department);
        setLecturerId(lecturerId);
        setSpecialization(specialization);
        this.assignedCourses = new ArrayList<>();
    }

    // Getters
    public Department getDepartment() {
        return department;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public ArrayList<Course> getAssignedCourses() {
        return assignedCourses;
    }

    // Setters
    public void setDepartment(Department department) {
        if (department == null)
            throw new IllegalArgumentException("Department cannot be null.");
        this.department = department;
    }

    public void setLecturerId(String lecturerId) {
        if (lecturerId == null || lecturerId.isBlank())
            throw new IllegalArgumentException("Lecturer ID must not be blank.");
        this.lecturerId = lecturerId;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isBlank())
            throw new IllegalArgumentException("Specialization must not be blank.");
        this.specialization = specialization;
    }

    // For Admin use only
    public void addAssignedCourse(Course course) {
        if (course == null)
            throw new IllegalArgumentException("Course cannot be null.");
        for (Course c : assignedCourses) {
            if (c.getCourseId().equals(course.getCourseId()))
                throw new IllegalArgumentException("Course already assigned: " + course.getCourseId());
        }
        assignedCourses.add(course);
    }

    public void removeAssignedCourse(String courseId) {
        if (courseId == null || courseId.isBlank())
            throw new IllegalArgumentException("Course ID must not be blank.");
        Course target = null;
        for (Course c : assignedCourses) {
            if (c.getCourseId().equals(courseId)) {
                target = c;
                break;
            }
        }
        if (target == null)
            throw new IllegalArgumentException("Course not found: " + courseId);
        assignedCourses.remove(target);
    }

    // View assigned courses
    public void viewCourses() {
        System.out.println("===== My Courses =====");
        if (assignedCourses.isEmpty()) {
            System.out.println("No courses assigned.");
            return;
        }
        for (Course c : assignedCourses) {
            System.out.println("- " + c.getCourseId() + " | " + c.getCourseName());
        }
    }

    public boolean viewStudentInCourse(String courseId) {
        if (courseId == null || courseId.isBlank())
            throw new IllegalArgumentException("Course ID must not be blank.");
        Course target = null;
        for (Course c : assignedCourses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                target = c;
                break;
            }
        }
        if (target == null)
            throw new IllegalArgumentException("Course not found: " + courseId);
        System.out.println("===== Students in " + target.getCourseName() + " =====");
        if (target.listOfEnroll.isEmpty()) {
            System.out.println("No students enrolled in this course.");
            return false;
        }
        for (Enrollment e : target.listOfEnroll) {
            System.out.println(e.getStudent().getStudentId() + super.space( 15,e.getStudent().getStudentId())
                    + e.getStudent().getName()
                    + super.space(15, courseId) + " Score: " + (e.getScore() == -1 ? "Not graded yet" : e.getScore()));
        }
        return true;
    }

    public Enrollment findEnrollment(String studentId, String courseId) {
        for (Course c : assignedCourses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                for (Enrollment e : c.listOfEnroll) {
                    if (e.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                        return e;
                    }
                }
            }

        }
        return new Enrollment(null, null);

    }

    public void assignGrade(Scanner scanner) {
        boolean done = false;

        try {
            while (!done) {
                String courseId;
                System.out.println("Enter course ID to view enrolled students: ");
                courseId = scanner.nextLine();
                boolean hasStudents = this.viewStudentInCourse(courseId);
                if (!hasStudents) {
                    return;
                }
                System.out.println("Enter student ID to assign grade: ");
                String studentId = scanner.nextLine();
                Enrollment enrollment = this.findEnrollment(studentId, courseId);
                if (enrollment == null) {
                    System.out.println("Cannot find student enrollment for given student and course Id.");
                    continue;
                }
                System.out.println("Enter grade ( 0-100):");
                try {
                    int grade = Integer.parseInt(scanner.nextLine());
                    if (grade < 0 || grade > 100) {
                        System.out.println("Grade must be between 0 and 100.");
                        continue;
                    }
                    enrollment.setScore(grade);
                    System.out.println("Grade assigned successfully: " + enrollment.getStudent().getName() + " | "
                            + enrollment.getCourse().getCourseId() + " | " + grade);
                    done = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid grade input. Must be a number between 0 and 100.");
                } catch (IllegalArgumentException e) {
                    System.out.println(" Error assign grade: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid grade.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void dashboard(Lecturer lecturer, Scanner scanner) {
        boolean exit = false;
        try {
            while (!exit) {
                System.out.println("\n===== MENU =====");
                System.out.println("1. View Profile");
                System.out.println("2. View My Courses");
                System.out.println("3. Assign Grade");
                System.out.println("4. View Course Students");
                System.out.println("5. Logout");
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
                        lecturer.assignGrade( scanner);
                        break;
                    case 4:
                        try {
                            // lecturer.viewCourseGrades();
                            String courseId;
                            System.out.println("Enter course ID to view enrolled students: ");
                            courseId = scanner.nextLine();
                            lecturer.viewStudentInCourse(courseId);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Exiting Lecturer Test. Goodbye!");
                        return;
                        
                        
                    default:
                        System.out.println("Invalid choice. Must be 1-5.");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
    }
}

    // Profile
    @Override
    public void viewProfile() {
        System.out.println("===== Lecturer Profile =====");
        System.out.println("Name           : " + getName());
        System.out.println("Email          : " + getEmail());
        System.out.println("Lecturer ID    : " + lecturerId);
        System.out.println("Department     : " + department.getDepartmentName());
        System.out.println("Specialization : " + specialization);
        // viewCourses();
    }

    @Override
    public String toString() {
        return "Lecturer[" + lecturerId + "] " + getName() + " | Department: "
                + department.getDepartmentCode() + " | Specialization: " + specialization;
    }
}