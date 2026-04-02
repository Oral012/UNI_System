package uni.system.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import uni.system.model.Course;
import uni.system.model.Enrollment;

public class Student extends User {
    private Department department;
    private String major;
    private String studentId;
    private int yearLevel;
    ArrayList<Enrollment> enrollment;

    public Student(String name, String email, String password, String studentId, Department department, String major,
            int yearLevel) {
        super(name, email, password, Role.STUDENT); // use constructor of Parent class ( User class)
        setDepartment(department);
        setMajor(major);
        setYearLevel(yearLevel);
        setStudentId(studentId);
        enrollment = new ArrayList<>();
    }

    // Setters methods
    public void setDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department must not be null.");
        }
        this.department = department;
    }

    public void setYearLevel(int yearLevel) {
        if (yearLevel < 1 || yearLevel > 4) {
            throw new IllegalArgumentException("Year level must be between 1 and 4.");
        }
        this.yearLevel = yearLevel;
    }

    public void setMajor(String major) {
        if (major.isBlank()) {
            throw new IllegalArgumentException("major must not be blank.");
        }
        this.major = major;
    }

    public void setStudentId(String studentId) {
        if (studentId.isBlank())
            throw new IllegalArgumentException("Student Id must not be blank.");
        this.studentId = studentId;
    }

    // Getters methods
    public int getYearLevel() {
        return yearLevel;
    }

    public Department getDepartment() {
        return department;
    }

    public String getMajor() {
        return major;
    }

    public String getStudentId() {
        return studentId;
    }
    public String grading(int score ) {
        if ( score > 80) {
            return "A";
        } else if ( score > 70) {
            return "B";
        } else if ( score > 60) {
            return "C";
        } else if ( score > 50) {
            return "D";
        } else {
            return "F";
        }
    }
    // methods
    @Override
    public void viewProfile() {
        System.out.println("----------------------------------Student Profile----------------------------------");
        System.out.println("Name = " + getName() + "\n"
                + "Student ID = " + getStudentId() + "\n"
                + "Email = " + getEmail() + "\n"
                + "Department = " + getDepartment() + "\n"
                + "Major = " + getMajor() + "\n"
                + "Year Level = " + getYearLevel() + "\n");
        System.out.println("----------------------------------------------------------------------------------");
    }
    public void viewGrade() {
        System.out.println("-------------------- Grade Report --------------------");
        if ( enrollment.isEmpty()) {
            System.out.println("No enrolled courses.");
        } else {
            System.out.println("Course Name"+ super.space( 30,"Course Name") + "Score");
            for ( Enrollment e : enrollment) {
                System.out.println(e.getCourse().getCourseName() + super.space(30, e.getCourse().getCourseName()) +
                        (e.getScore() == -1 ? "Not Graded" : e.getScore() + "-" + grading(e.getScore())));
            }
        }
    }
    public void addEnrollment(String courseId, ArrayList<Course> courses) {
        if (enrollment.size() > 7) {
            System.out.println("You can not enroll more than 7 courses.");
            return;
        }
        // if course already enrolled
        for ( Enrollment e : enrollment) {
            if ( e.getCourse().getCourseId().equalsIgnoreCase(courseId)) {
                System.out.println("You have already enrolled this course.");
                return;
            }
        }

        if (courseId.isBlank())
            throw new IllegalArgumentException("CourseId must not be blank.");
        Course course = null;
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId) &&
                    c.getDepartment().getDepartmentCode().equalsIgnoreCase(this.getDepartment().getDepartmentCode())
                    && c.getYearLevel() == this.getYearLevel()) {
                course = c;
                System.out.println( this.getName() + " enrolled in " + course.getCourseName() + " successfully.");
                break;
            }
        }
        if (course == null) {
            System.out.println("Course not found or not available for your department and year level.");
            return;
        }
        Enrollment e = new Enrollment(this, course);
        enrollment.add(e);
        course.listOfEnroll.add(e);
    }

    public void removeEnrollment(String courseId) {
        if (enrollment.isEmpty()) {
            System.out.println("You haven't enrolled any courses yet.");
        }
        if (courseId.isBlank())
            throw new IllegalArgumentException("CourseId must not be blank.");

        Enrollment e = null;
        for (Enrollment enrollment : enrollment) {
            if (enrollment.getCourse().getCourseId().equalsIgnoreCase(courseId)) {
                e = enrollment;
                break;
            }
        }
        if (e == null) {
            throw new IllegalArgumentException("Not enrolled this course.");
        }
        enrollment.remove(e);
        System.out.println( this.getName() + " dropped " + e.getCourse().getCourseName() + " successfully.");
    }

    public void viewEnrolledCourses() {
        System.out.println("---------------- Enrolled Courses ----------------");
        if (enrollment.isEmpty()) {
            System.out.println("No enrolled courses.");
        } else {
            for (Enrollment e : enrollment) {
                // System.out.println(e.getCourse().getCourseId() + " - " + e.getCourse().getCourseName());
                System.out.println(e);
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public void viewAvailableCourses(ArrayList<Course> courses) {
        System.out.println("---------------- Available Courses ----------------");
        if (courses.isEmpty()) {
            System.out.println("No available courses.");
        } else {
            for (Course c : courses) {
                boolean enrolled = false;
                if (c.getDepartment().getDepartmentCode().equals(this.getDepartment().getDepartmentCode())
                        && c.getYearLevel() == this.getYearLevel()) {
                    for (Enrollment e : enrollment) {
                        if (e.getCourse().getCourseId().equalsIgnoreCase(c.getCourseId())) {
                            enrolled = true;
                            break;
                        }
                    }
                    if ( !enrolled) {
                        System.out.println(c.getCourseId() + " - " + c.getCourseName());
                    }
                }
            }
        }
        System.out.println("--------------------------------------------------");

    }

    public void dashboard(Student student, ArrayList<Course> courses) {
        // Accept input
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("------------------- Student Dashboard -------------------");
            System.out.println("1. View Profile");
            System.out.println("2. View Courses");
            System.out.println("3. View Available Courses");
            System.out.println("4. Add New Enrollment");
            System.out.println("5. Drop Course");
            System.out.println("6. View Grade");
            System.out.println("7. Logout");
            System.out.println("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch ( InputMismatchException e) {
                System.out.println("Invalid input.");
            } catch ( NoSuchElementException e) {
                System.out.println("Thanks for using the system.");
                scanner.close();
                return;
            }
            switch (choice) {
                case 1:
                    student.viewProfile();
                    break;
                case 2:
                    student.viewEnrolledCourses();
                    break;
                case 3:
                    student.viewAvailableCourses(courses);
                    break;
                case 4:
                    System.out.println("Enter course ID to enroll: ");
                    try {
                        String courseId = scanner.next();
                        student.addEnrollment(courseId, courses);
                    } catch ( InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                    } 
                    
                    break;
                case 5:
                    System.out.println("Enter course ID to drop: ");
                    String courseIdToDrop = scanner.next();
                    student.removeEnrollment(courseIdToDrop);
                    break;
                case 6: 
                    student.viewGrade();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    scanner.nextLine();
            }

        } while (true);
    }

}
    
