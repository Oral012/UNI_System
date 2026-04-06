package uni.system.model;

import java.util.ArrayList;
import java.util.Scanner;
import uni.system.io.UserFileIO;

public class Admin extends User {
    private String adminId;
    public Admin(String name, String email, String password, String adminId) {
        super(name, email, password, Role.ADMIN);
        if (adminId == null || adminId.isBlank())
            throw new IllegalArgumentException("Id cannot be empty.");
        this.adminId = adminId;
    }
    

    public String getAdminId() {
        return adminId;
    }

    public void adminDashboard(University university) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=========== ADMIN DASHBOARD ===========");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Update User");
            System.out.println("4. View All Users");
            System.out.println("5. View My Profile");
            System.out.println("6. View Users From File");
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
                case 1 -> addUser(university, scanner);
                case 2 -> removeUser(university, scanner);
                case 3 -> updateUser(university, scanner);
                case 4 -> viewAllUsers(university);
                case 5 -> viewProfile();
                case 6 -> UserFileIO.printAllRecords();
                case 0 -> { running = false; System.out.println("Exiting admin dashboard."); }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ─── Find helpers ─────────────────────────────────────────────────────────────

    private Student findStudentById(University university, String id) {
        for (User user : university.getUsers())
            if (user instanceof Student s && s.getStudentId().equalsIgnoreCase(id)) return s;
        return null;
    }

    private Lecturer findLecturerById(University university, String id) {
        for (User user : university.getUsers())
            if (user instanceof Lecturer l && l.getLecturerId().equalsIgnoreCase(id)) return l;
        return null;
    }

    private Manager findManagerById(University university, String id) {
        for (User user : university.getUsers())
            if (user instanceof Manager m && m.getManagerId().equalsIgnoreCase(id)) return m;
        return null;
    }

    private Department findDepartmentByCode(University university, String code) {
        for (Department d : university.getDepartments())
            if (d.getDepartmentCode().equalsIgnoreCase(code)) return d;
        return null;
    }

    // ─── Get-all helpers ──────────────────────────────────────────────────────────

    private ArrayList<Student> getAllStudents(University university) {
        ArrayList<Student> list = new ArrayList<>();
        for (User user : university.getUsers())
            if (user instanceof Student s) list.add(s);
        return list;
    }

    private ArrayList<Lecturer> getAllLecturers(University university) {
        ArrayList<Lecturer> list = new ArrayList<>();
        for (User user : university.getUsers())
            if (user instanceof Lecturer l) list.add(l);
        return list;
    }

    private ArrayList<Manager> getAllManagers(University university) {
        ArrayList<Manager> list = new ArrayList<>();
        for (User user : university.getUsers())
            if (user instanceof Manager m) list.add(m);
        return list;
    }

    // ─── Add ──────────────────────────────────────────────────────────────────────

    private void addStudent(University university, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        if (findStudentById(university, studentId) != null) {
            System.out.println("Student ID already exists.");
            return;
        }
        System.out.print("Enter name: ");        String name     = scanner.nextLine();
        System.out.print("Enter email: ");       String email    = scanner.nextLine();
        System.out.print("Enter password: ");    String password = scanner.nextLine();
        System.out.print("Enter major: ");       String major    = scanner.nextLine();
        System.out.print("Enter year level: ");  int yearLevel   = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter dept code: ");   Department dept = findDepartmentByCode(university, scanner.nextLine().trim());

        try {
            Student student = new Student(name, email, password, studentId, dept, major, yearLevel);
            university.addUser(student);
            UserFileIO.writeUser(student);
            System.out.println("Student '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    private void addLecturer(University university, Scanner scanner) {
        System.out.print("Enter Lecturer ID: ");
        String lecturerId = scanner.nextLine();
        if (findLecturerById(university, lecturerId) != null) {
            System.out.println("Lecturer ID already exists.");
            return;
        }
        System.out.print("Enter name: ");           String name           = scanner.nextLine();
        System.out.print("Enter email: ");          String email          = scanner.nextLine();
        System.out.print("Enter password: ");       String password       = scanner.nextLine();
        System.out.print("Enter specialization: "); String specialization = scanner.nextLine();
        System.out.print("Enter dept code: ");      Department dept       = findDepartmentByCode(university, scanner.nextLine().trim());

        try {
            Lecturer lecturer = new Lecturer(name, email, password, dept, lecturerId, specialization);
            university.addUser(lecturer);
            UserFileIO.writeUser(lecturer);
            System.out.println("Lecturer '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    private void addManager(University university, Scanner scanner) {
        System.out.print("Enter Manager ID: ");
        String managerId = scanner.nextLine();
        if (findManagerById(university, managerId) != null) {
            System.out.println("Manager ID already exists.");
            return;
        }
        System.out.print("Enter name: ");     String name     = scanner.nextLine();
        System.out.print("Enter email: ");    String email    = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();

        try {
            Manager manager = new Manager(name, email, password, managerId);
            university.addUser(manager);
            UserFileIO.writeUser(manager);
            System.out.println("Manager '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    private void addUser(University university, Scanner scanner) {
        System.out.println("1. Student  2. Lecturer  3. Manager");
        System.out.print("Enter your choice: ");
        try {
            switch (Integer.parseInt(scanner.nextLine().trim())) {
                case 1 -> addStudent(university, scanner);
                case 2 -> addLecturer(university, scanner);
                case 3 -> addManager(university, scanner);
                default -> System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    // ─── Remove ───────────────────────────────────────────────────────────────────

    private void removeStudent(University university, Scanner scanner) {
        System.out.print("Enter Student ID to remove: ");
        Student student = findStudentById(university, scanner.nextLine());
        if (student == null) { System.out.println("Student not found."); return; }
        university.deleteUser(student);
        UserFileIO.deleteUserRecord(student.getStudentId());
        System.out.println("Student '" + student.getName() + "' removed.");
    }

    private void removeLecturer(University university, Scanner scanner) {
        System.out.print("Enter Lecturer ID to remove: ");
        Lecturer lecturer = findLecturerById(university, scanner.nextLine());
        if (lecturer == null) { System.out.println("Lecturer not found."); return; }
        university.deleteUser(lecturer);
        UserFileIO.deleteUserRecord(lecturer.getLecturerId());
        System.out.println("Lecturer '" + lecturer.getName() + "' removed.");
    }

    private void removeManager(University university, Scanner scanner) {
        System.out.print("Enter Manager ID to remove: ");
        Manager manager = findManagerById(university, scanner.nextLine());
        if (manager == null) { System.out.println("Manager not found."); return; }
        university.deleteUser(manager);
        UserFileIO.deleteUserRecord(manager.getManagerId());
        System.out.println("Manager '" + manager.getName() + "' removed.");
    }

    private void removeUser(University university, Scanner scanner) {
        System.out.println("1. Student  2. Lecturer  3. Manager");
        System.out.print("Enter your choice: ");
        try {
            switch (Integer.parseInt(scanner.nextLine().trim())) {
                case 1 -> removeStudent(university, scanner);
                case 2 -> removeLecturer(university, scanner);
                case 3 -> removeManager(university, scanner);
                default -> System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    // ─── Update ───────────────────────────────────────────────────────────────────

    private void updateStudent(University university, Scanner scanner) {
        System.out.print("Enter Student ID to update: ");
        Student student = findStudentById(university, scanner.nextLine().trim());
        if (student == null) { System.out.println("Student not found."); return; }

        System.out.print("New name: ");        student.setName(scanner.nextLine().trim());
        System.out.print("New email: ");       student.setEmail(scanner.nextLine().trim());
        System.out.print("New password: ");    student.setPassword(scanner.nextLine().trim());
        System.out.print("New major: ");       student.setMajor(scanner.nextLine().trim());
        System.out.print("New year level: ");  student.setYearLevel(Integer.parseInt(scanner.nextLine().trim()));
        System.out.print("New dept code: ");   student.setDepartment(findDepartmentByCode(university, scanner.nextLine().trim()));

        UserFileIO.updateUserRecord(student);
        System.out.println("Student updated successfully.");
    }

    private void updateLecturer(University university, Scanner scanner) {
        System.out.print("Enter Lecturer ID to update: ");
        Lecturer lecturer = findLecturerById(university, scanner.nextLine().trim());
        if (lecturer == null) { System.out.println("Lecturer not found."); return; }

        System.out.print("New name: ");           lecturer.setName(scanner.nextLine().trim());
        System.out.print("New email: ");          lecturer.setEmail(scanner.nextLine().trim());
        System.out.print("New password: ");       lecturer.setPassword(scanner.nextLine().trim());
        System.out.print("New specialization: "); lecturer.setSpecialization(scanner.nextLine().trim());
        System.out.print("New dept code: ");      lecturer.setDepartment(findDepartmentByCode(university, scanner.nextLine().trim()));

        UserFileIO.updateUserRecord(lecturer);
        System.out.println("Lecturer updated successfully.");
    }

    private void updateManager(University university, Scanner scanner) {
        System.out.print("Enter Manager ID to update: ");
        Manager manager = findManagerById(university, scanner.nextLine().trim());
        if (manager == null) { System.out.println("Manager not found."); return; }

        System.out.print("New name: ");     manager.setName(scanner.nextLine().trim());
        System.out.print("New email: ");    manager.setEmail(scanner.nextLine().trim());
        System.out.print("New password: "); manager.setPassword(scanner.nextLine().trim());

        UserFileIO.updateUserRecord(manager);
        System.out.println("Manager updated successfully.");
    }

    private void updateUser(University university, Scanner scanner) {
        System.out.println("1. Student  2. Lecturer  3. Manager");
        System.out.print("Enter your choice: ");
        try {
            switch (Integer.parseInt(scanner.nextLine().trim())) {
                case 1 -> updateStudent(university, scanner);
                case 2 -> updateLecturer(university, scanner);
                case 3 -> updateManager(university, scanner);
                default -> System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    // ─── View ─────────────────────────────────────────────────────────────────────

    private void viewAllUsers(University university) {
        System.out.println("==================== All Users ====================");
        if (university.getUsers().isEmpty()) {
            System.out.println("No users in the system.");
            return;
        }
        System.out.println("--- Managers ---");
        ArrayList<Manager> managers = getAllManagers(university);
        if (managers.isEmpty()) System.out.println("  None.");
        else for (Manager m : managers)
            System.out.printf("%-15s %-15s %-25s%n", m.getManagerId(), m.getName(), m.getEmail());

        System.out.println("--- Lecturers ---");
        ArrayList<Lecturer> lecturers = getAllLecturers(university);
        if (lecturers.isEmpty()) System.out.println("  None.");
        else for (Lecturer l : lecturers)
            System.out.printf("%-15s %-15s %-25s %-15s%n",
                    l.getLecturerId(), l.getName(), l.getEmail(), l.getDepartment().getDepartmentCode());

        System.out.println("--- Students ---");
        ArrayList<Student> students = getAllStudents(university);
        if (students.isEmpty()) System.out.println("  None.");
        else for (Student s : students)
            System.out.printf("%-15s %-15s %-25s %-10s Yr%d%n",
                    s.getStudentId(), s.getName(), s.getEmail(),
                    s.getDepartment().getDepartmentCode(), s.getYearLevel());

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