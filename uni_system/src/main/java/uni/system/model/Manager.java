package uni.system.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends User {
    private String managerId;

    public Manager(String name, String email, String password, String managerId) {
        super(name, email, password, Role.MANAGER);
        setManagerId(managerId);
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        if (managerId == null || managerId.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.managerId = managerId;
    }
    
    @Override
    public void viewProfile() {
        System.out.println("Name = " + getName() + "\n"
                + "Email = " + getEmail() + "\n"
                + "Role = " + getRole() + "\n"
                + "Manager ID = " + getManagerId());
    }

    public void dashboard(University university, ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        int choice ;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
          
            switch (choice) {
                case 1:
                    viewAllStudents(university);
                    break;
                case 2:
                    viewAllLecturers(university);
                    break;
                case 3:
                    createCourse(university, courses);
                    break;
                case 4:
                    deleteCourse(courses);
                    break;
                case 5:
                    viewAllCourses(courses);
                    break;
                case 6:
                    assignLecturerToCourse(university, courses);
                    break;
                case 7:
                    printCourseEnrollmentReport(university, courses);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("--- STUDENT MANAGEMENT ---");
        System.out.println("1. View All Students");
        System.out.println("--- LECTURER MANAGEMENT ---");
        System.out.println("2. View All Lecturers");
        System.out.println("--- COURSE MANAGEMENT ---");
        System.out.println("3. Create Course");
        System.out.println("4. Delete Course");
        System.out.println("5. View All Courses");
        System.out.println("6. Assign Lecturer to Course");
        System.out.println("--- REPORTS ---");
        System.out.println("7. Course Enrollment Report");
        System.out.println("0. Logout");
    }
    private void printDepartmentCode(University university) {
                    String deptCodes = "";
            for( Department dept : university.getDepartments()){
                deptCodes += dept.getDepartmentCode() + " ";
            }
             System.out.print("Department code (" + deptCodes + ")");
        }


    private void viewAllStudents(University university) {
        ArrayList<Student> students = getAllStudents(university);
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student.getStudentId() + "  " + student.getName() + "  "
                    + student.getDepartment().getDepartmentCode() + "  Year " + student.getYearLevel());
        }
    }



    private void viewAllLecturers(University university) {
        ArrayList<Lecturer> lecturers = getAllLecturers(university);
        if (lecturers.isEmpty()) {
            System.out.println("No lecturers found.");
            return;
        }
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getLecturerId() + "  " + lecturer.getName() + "  "
                    + lecturer.getDepartment().getDepartmentCode() + "  " + lecturer.getSpecialization());
        }
    }

    private void createCourse(University university, ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
       
            System.out.print("Course ID: ");
            String courseId = scanner.next();
            if (findCourseById(courses, courseId) != null) {
                System.out.println("Course ID already exists.");
                return;
            }
            scanner.nextLine();
            System.out.print("Course name: ");
            String courseName = scanner.nextLine();
            System.out.print("Credits: ");
            double credit = scanner.nextDouble();
            printDepartmentCode(university);
            String deptCode = scanner.next();
            Department department = findDepartmentByCode(university, deptCode);
            if (department == null) {
                System.out.println("Department not found.");
                return;
            }
            System.out.print("Year level (1-4): ");
            int yearLevel = scanner.nextInt();

            Course course = new Course(courseId, courseName, credit, department, yearLevel);
            courses.add(course);
    }

    private void deleteCourse(ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID to delete: ");
        String courseId = scanner.next();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equalsIgnoreCase(courseId)) {
                courses.remove(i);
                return;
            }
        }
        System.out.println("Course not found.");
    }

    private void assignLecturerToCourse(University university, ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lecturer ID: ");
        String lecturerId = scanner.next();
        Lecturer lecturer = findLecturerById(university, lecturerId);
        if (lecturer == null) {
            System.out.println("Lecturer not found.");
            return;
        }

        System.out.print("Enter course ID: ");
        String courseId = scanner.next();
        Course course = findCourseById(courses, courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        for (Course assigned : lecturer.getAssignedCourses()) {
            if (assigned.getCourseId().equals(course.getCourseId())) {
                System.out.println("This course is already assigned to the lecturer.");
                return;
            }
        }

        lecturer.getAssignedCourses().add(course);
    }

    private void printCourseEnrollmentReport(University university, ArrayList<Course> courses) {
        System.out.println("===== Course Enrollment Report =====");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        ArrayList<Student> students = getAllStudents(university);
        for (Course course : courses) {
            int count = 0;
            for (Student student : students) {
                for (Enrollment enrollment : student.enrollment) {
                    if (enrollment.getCourse() != null
                            && enrollment.getCourse().getCourseId().equals(course.getCourseId())
                            && enrollment.isActive()) {
                        count++;
                    }
                }
            }
            System.out.println(course.getCourseId() + "  " + course.getCourseName() + ": " + count + " students");
        }
    }
  

    public void viewAllCourses(ArrayList<Course> coursesTaught) {
        if (coursesTaught.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course c : coursesTaught) {
            System.out.println(c);
        }
    }

    private Department findDepartmentByCode(University university, String code) {
        for (Department department : university.getDepartments()) {
            if (department.getDepartmentCode().equalsIgnoreCase(code)) {
                return department;
            }
        }
        return null;
    }

    private Lecturer findLecturerById(University university, String lecturerId) {
        for (User user : university.getUsers()) {
            if (user instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) user;
                if (lecturer.getLecturerId().equalsIgnoreCase(lecturerId)) {
                    return lecturer;
                }
            }
        }
        return null;
    }

    private Course findCourseById(ArrayList<Course> courses, String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equalsIgnoreCase(courseId)) {
                return course;
            }
        }
        return null;
    }

    private ArrayList<Student> getAllStudents(University university) {
        ArrayList<Student> students = new ArrayList<>();
        for (User user : university.getUsers()) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }

    private ArrayList<Lecturer> getAllLecturers(University university) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (User user : university.getUsers()) {
            if (user instanceof Lecturer) {
                lecturers.add((Lecturer) user);
            }
        }
        return lecturers;
    }
}