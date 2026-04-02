package uni.system.model;

import java.util.ArrayList;
import java.util.Scanner;
import uni.system.io.UserFileIO;
import uni.system.model.*;
public class Admin extends User {
    private String adminId;

    public Admin(String name, String email, String password, String adminId) {
        super(name, email, password, Role.ADMIN);
        if (adminId == null || adminId.isBlank()) {
            throw new IllegalArgumentException("Id cannot be empty.");
        }
        this.adminId = adminId;
    }

    private String getAdminId() {
        return adminId;
    }

    public void adminDashboard(University university) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=========== ADMIN DASHBOARD ===========");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. View All Users");
            System.out.println("4. View My Profile");
            System.out.println("5. View Users From File");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addUser(university);
                case 2 -> removeUser(university);
                case 3 -> viewAllUsers(university);
                case 4 -> viewProfile();
                case 5 -> UserFileIO.printAllRecords();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting admin dashboard.");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private Student findStudentById(University university, String studentId) {
        for (User user : university.getUsers()) {
            if (user instanceof Student s && s.getStudentId().equalsIgnoreCase(studentId))
                return s;
        }
        return null;
    }

    private Lecturer findLecturerById(University university, String lecturerId) {
        for (User user : university.getUsers()) {
            if (user instanceof Lecturer l && l.getLecturerId().equalsIgnoreCase(lecturerId))
                return l;
        }
        return null;
    }

    private Manager findManagerById(University university, String managerId) {
        for (User user : university.getUsers()) {
            if (user instanceof Manager m && m.getManagerId().equalsIgnoreCase(managerId))
                return m;
        }
        return null;
    }

    private ArrayList<Student> getAllStudents(University university) {
        ArrayList<Student> students = new ArrayList<>();
        for (User user : university.getUsers())
            if (user instanceof Student s) students.add(s);
        return students;
    }

    private ArrayList<Lecturer> getAllLecturers(University university) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (User user : university.getUsers())
            if (user instanceof Lecturer l) lecturers.add(l);
        return lecturers;
    }

    private ArrayList<Manager> getAllManagers(University university) {
        ArrayList<Manager> managers = new ArrayList<>();
        for (User user : university.getUsers())
            if (user instanceof Manager m) managers.add(m);
        return managers;
    }

    private Department findDepartment(University university, Scanner scanner) {
        System.out.println("Available departments:");
        for (Department d : university.getDepartments())
            System.out.println(" - " + d);
        System.out.print("Enter department code: ");
        String deptCode = scanner.nextLine();
        for (Department d : university.getDepartments())
            if (d.getDepartmentCode().equalsIgnoreCase(deptCode))
                return d;
        System.out.println("Department not found.");
        return null;
    }

    private void addStudent(University university, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (findStudentById(university, studentId) != null) {
            System.out.println("Student ID '" + studentId + "' already exists.");
            return;
        }

        System.out.print("Enter name: ");     
        String name = scanner.nextLine();
        System.out.print("Enter email: ");    
        String email = scanner.nextLine();
        System.out.print("Enter password: "); 
        String password = scanner.nextLine();
        System.out.print("Enter major: ");    
        String major = scanner.nextLine();

        int yearLevel;
        System.out.print("Enter year level (1-4): ");
        try {
            yearLevel = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year level. Please enter a number.");
            return;
        }

        Department department = findDepartment(university, scanner);
        if (department == null) return;

        try {
            Student student = new Student(name, email, password, studentId, department, major, yearLevel);
            university.addUser(student);
            UserFileIO.writeUser(student);  
            System.out.println("Student '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create student: " + e.getMessage());
        }
    }

    private void addLecturer(University university, Scanner scanner) {
        System.out.print("Enter Lecturer ID: ");
        String lecturerId = scanner.nextLine();

        if (findLecturerById(university, lecturerId) != null) {
            System.out.println("Lecturer ID '" + lecturerId + "' already exists.");
            return;
        }

        System.out.print("Enter name: ");           
        String name  = scanner.nextLine();
        System.out.print("Enter email: ");           
        String email = scanner.nextLine();
        System.out.print("Enter password: ");        
        String password = scanner.nextLine();
        System.out.print("Enter specialization: ");  
        String specialization = scanner.nextLine();

        Department department = findDepartment(university, scanner);
        if (department == null) return;

        try {
            Lecturer lecturer = new Lecturer(name, email, password, department, lecturerId, specialization);
            university.addUser(lecturer);
            UserFileIO.writeUser(lecturer);  
            System.out.println("Lecturer '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create lecturer: " + e.getMessage());
        }
    }

    private void addManager(University university, Scanner scanner) {
        System.out.print("Enter Manager ID: ");
        String managerId = scanner.nextLine();

        if (findManagerById(university, managerId) != null) {
            System.out.println("Manager ID '" + managerId + "' already exists.");
            return;
        }

        System.out.print("Enter name: ");     
        String name = scanner.nextLine();
        System.out.print("Enter email: ");    
        String email = scanner.nextLine();
        System.out.print("Enter password: "); 
        String password = scanner.nextLine();

        try {
            Manager manager = new Manager(name, email, password, managerId);
            university.addUser(manager);
            UserFileIO.writeUser(manager);  
            System.out.println("Manager '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create manager: " + e.getMessage());
        }
    }

    private void addUser(University university) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select user type to add:");
        System.out.println("1. Student");
        System.out.println("2. Lecturer");
        System.out.println("3. Manager");
        System.out.print("Enter your choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1 -> addStudent(university, scanner);
            case 2 -> addLecturer(university, scanner);
            case 3 -> addManager(university, scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void removeUser(University university) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select user type to remove:");
        System.out.println("1. Student");
        System.out.println("2. Lecturer");
        System.out.println("3. Manager");
        System.out.print("Enter your choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1 -> removeStudent(university, scanner);
            case 2 -> removeLecturer(university, scanner);
            case 3 -> removeManager(university, scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void removeStudent(University university, Scanner scanner) {
        System.out.print("Enter Student ID to remove: ");
        Student student = findStudentById(university, scanner.nextLine());
        if (student == null) { System.out.println("Student not found."); return; }
        university.deleteUser(student);
        System.out.println("Student '" + student.getName() + "' removed successfully.");
    }

    private void removeLecturer(University university, Scanner scanner) {
        System.out.print("Enter Lecturer ID to remove: ");
        Lecturer lecturer = findLecturerById(university, scanner.nextLine());
        if (lecturer == null) { System.out.println("Lecturer not found."); return; }
        university.deleteUser(lecturer);
        System.out.println("Lecturer '" + lecturer.getName() + "' removed successfully.");
    }

    private void removeManager(University university, Scanner scanner) {
        System.out.print("Enter Manager ID to remove: ");
        Manager manager = findManagerById(university, scanner.nextLine());
        if (manager == null) { System.out.println("Manager not found."); return; }
        university.deleteUser(manager);
        System.out.println("Manager '" + manager.getName() + "' removed successfully.");
    }

    private void viewAllUsers(University university) {
        System.out.println("==================== All Users ====================");
        if (university.getUsers().isEmpty()) {
            System.out.println("No users in the system.");
        } else {
            System.out.println("--- Managers ---");
            ArrayList<Manager> managers = getAllManagers(university);
            if (managers.isEmpty()) System.out.println("  None.");
            else for (Manager m : managers)
                System.out.printf("%-15s  %-15s %-25s%n", m.getManagerId(), m.getName(), m.getEmail());

            System.out.println("--- Lecturers ---");
            ArrayList<Lecturer> lecturers = getAllLecturers(university);
            if (lecturers.isEmpty()) System.out.println("  None.");
            else for (Lecturer l : lecturers)
                System.out.printf("%-15s  %-15s %-25s %-15s%n", l.getLecturerId(), l.getName(), l.getEmail(), l.getDepartment().getDepartmentCode());

            System.out.println("--- Students ---");
            ArrayList<Student> students = getAllStudents(university);
            if (students.isEmpty()) System.out.println("  None.");
            else for (Student s : students)
                System.out.printf("%-15s %-15s %-25s %-10s Yr%d%n", s.getStudentId(), s.getName(), s.getEmail(), s.getDepartment().getDepartmentCode(), s.getYearLevel());
        }
        System.out.println("====================================================");
    }

    @Override
    public void viewProfile() {
        System.out.println("===== Admin Profile =====");
        System.out.println("Name     = " + getName());
        System.out.println("Email    = " + getEmail());
        System.out.println("Role     = " + getRole());
        System.out.println("Admin ID = " + getAdminId());
    }
}