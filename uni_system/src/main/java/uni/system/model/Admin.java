package uni.system.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private String adminId;

    public Admin(String name, String email, String password, String adminId) {
        super(name, email, password, Role.ADMIN);
        setAdminId(adminId);
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        if (adminId.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.adminId = adminId;
    }
    
    @Override
    public void viewProfile() {
        System.out.println("Name = " + getName() + "\n"
                + "Email = " + getEmail() + "\n"
                + "Role = " + getRole() + "\n"
                + "Admin ID = " + getAdminId());
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
                    addStudent(university);
                    break;
                case 2:
                    removeStudent(university);
                    break;
                case 3:
                    viewAllStudents(university);
                    break;

                case 5:
                    addLecturer(university);
                    break;
                case 6:
                    removeLecturer(university);
                    break;
                case 7:
                    viewAllLecturers(university);
                    break;
                case 8:
                    createCourse(university, courses);
                    break;
                case 9:
                    deleteCourse(courses);
                    break;
                case 10:
                    viewAllCourses(courses);
                    break;
                case 11:
                    assignLecturerToCourse(university, courses);
                    break;
                case 12:
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
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. View All Students");
        System.out.println("--- LECTURER MANAGEMENT ---");
        System.out.println("5. Add Lecturer");
        System.out.println("6. Remove Lecturer");
        System.out.println("7. View All Lecturers");
        System.out.println("--- COURSE MANAGEMENT ---");
        System.out.println("8. Create Course");
        System.out.println("9. Delete Course");
        System.out.println("10. View All Courses");
        System.out.println("11. Assign Lecturer to Course");
        System.out.println("--- REPORTS ---");
        System.out.println("12. Course Enrollment Report");
        System.out.println("0. Logout");
    }
    private void printDepartmentCode(University university) {
                    String deptCodes = "";
            for( Department dept : university.getDepartments()){
                deptCodes += dept.getDepartmentCode() + " ";
            }
             System.out.print("Department code (" + deptCodes + ")");
    private void addStudent(University university) {
        Scanner scanner = new Scanner(System.in);

            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("Email: ");
            String email = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            System.out.print("Student ID: ");
            String studentId = scanner.next();
            if (findStudentById(university, studentId) != null) {
                System.out.println("Student ID already exists.");
                return;
            }
            printDepartmentCode(university);
            String deptCode = scanner.next();
            Department department = findDepartmentByCode(university, deptCode);
            if (department == null) {
                System.out.println("Department not found.");
                return;
            }
            System.out.print("Major: ");
            String major = scanner.next();
            System.out.print("Year level (1-4): ");
            int yearLevel = scanner.nextInt();
            Student student = new Student(name, email, password, studentId, department, major, yearLevel);
            university.addUser(student);
    }

    private void removeStudent(University university) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to remove: ");
        String studentId = scanner.next();
        Student student = findStudentById(university, studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        university.deleteUser(student);
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



    private void addLecturer(University university) {
        Scanner scanner = new Scanner(System.in);

            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("Email: ");
            String email = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            System.out.print("Lecturer ID: ");
            String lecturerId = scanner.next();
            if (findLecturerById(university, lecturerId) != null) {
                System.out.println("Lecturer ID already exists.");
                return;
            }
            printDepartmentCode(university);
            String deptCode = scanner.next();
            Department department = findDepartmentByCode(university, deptCode);
            if (department == null) {
                System.out.println("Department not found.");
                return;
            }
            System.out.print("Specialization: ");
            String specialization = scanner.next();

            Lecturer lecturer = new Lecturer(name, email, password, department, lecturerId, specialization);
            university.addUser(lecturer);
    }

    private void removeLecturer(University university) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lecturer ID to remove: ");
        String lecturerId = scanner.next();
        Lecturer lecturer = findLecturerById(university, lecturerId);
        if (lecturer == null) {
            System.out.println("Lecturer not found.");
            return;
        }
        university.deleteUser(lecturer);
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

    private Student findStudentById(University university, String studentId) {
        for (User user : university.getUsers()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                if (student.getStudentId().equalsIgnoreCase(studentId)) {
                    return student;
                }
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
