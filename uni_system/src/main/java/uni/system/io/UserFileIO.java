
package uni.system.io;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import uni.system.model.*;


public class UserFileIO {

    private static final String FILE_PATH = "users.txt";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void writeUser(User user) {
        boolean isNewFile = !new File(FILE_PATH).exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (isNewFile) {
                writer.write("=== University User Records ===");
                writer.newLine();
                writer.write("-".repeat(100));
                writer.newLine();
            }

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String line = buildLine(user, timestamp);
            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Warning: Could not write user to file. " + e.getMessage());
        }
    }

    private static String buildLine(User user, String timestamp) {

        if (user instanceof Student s) {
            return String.join("|",
                    timestamp,
                    "STUDENT",
                    s.getStudentId(),
                    s.getName(),
                    s.getEmail(),
                    s.getDepartment().getDepartmentCode(),
                    s.getMajor(),
                    String.valueOf(s.getYearLevel())
            );

        } else if (user instanceof Lecturer l) {
            return String.join("|",
                    timestamp,
                    "LECTURER",
                    l.getLecturerId(),
                    l.getName(),
                    l.getEmail(),
                    l.getDepartment().getDepartmentCode(),
                    l.getSpecialization()
            );

        } else if (user instanceof Manager m) {
            return String.join("|",
                    timestamp,
                    "MANAGER",
                    m.getManagerId(),
                    m.getName(),
                    m.getEmail()
            );
        }

        return "";
    }

    public static void printAllRecords() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No user records file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading user records: " + e.getMessage());
        }
    }
    public static void deleteUserRecord(String id) {

    File inputFile = new File(FILE_PATH);
    File tempFile = new File("users_temp.txt");

    if (!inputFile.exists()) return;

    try (
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
    ) {

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.startsWith("=") || line.startsWith("-")) {
                writer.write(line);
                writer.newLine();
                continue;
            }

            String[] parts = line.split("\\|");

            if (parts.length < 3) continue;

            String recordId = parts[2];   // ← FIXED

            if (!recordId.equalsIgnoreCase(id)) {
                writer.write(line);
                writer.newLine();
            }
        }

    } catch (IOException e) {
        System.out.println("Error deleting user: " + e.getMessage());
    }

    inputFile.delete();
    tempFile.renameTo(inputFile);
}
    public static void updateUserRecord(User user) {
        String id;
        if      (user instanceof Student s)  id = s.getStudentId();
        else if (user instanceof Lecturer l) id = l.getLecturerId();
        else if (user instanceof Manager m)  id = m.getManagerId();
        else return;

        deleteUserRecord(id);
        writeUser(user);
    }

    public static void loadUsers(University university) {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) continue;

                if (line.startsWith("=") || line.startsWith("-") || line.contains("Timestamp"))
                    continue;

                String[] parts = line.split("\\|");

                if (parts.length < 3) continue;

                String role = parts[1];

                switch (role) {

                    case "STUDENT" -> {

                        String id = parts[2];
                        String name = parts[3];
                        String email = parts[4];
                        String deptCode = parts[5];
                        String major = parts[6];
                        int yearLevel = Integer.parseInt(parts[7]);

                        Department dept = null;

                        for (Department d : university.getDepartments()) {
                            if (d.getDepartmentCode().equalsIgnoreCase(deptCode)) {
                                dept = d;
                                break;
                            }
                        }

                        Student student = new Student(
                                name,
                                email,
                                "default",
                                id,
                                dept,
                                major,
                                yearLevel
                        );

                        university.addUser(student);
                    }

                    case "LECTURER" -> {

                        String id = parts[2];
                        String name = parts[3];
                        String email = parts[4];
                        String deptCode = parts[5];
                        String specialization = parts[6];

                        Department dept = null;

                        for (Department d : university.getDepartments()) {
                            if (d.getDepartmentCode().equalsIgnoreCase(deptCode)) {
                                dept = d;
                                break;
                            }
                        }

                        Lecturer lecturer = new Lecturer(
                                name,
                                email,
                                "default",
                                dept,
                                id,
                                specialization
                        );

                        university.addUser(lecturer);
                    }

                    case "MANAGER" -> {

                        String id = parts[2];
                        String name = parts[3];
                        String email = parts[4];

                        Manager manager = new Manager(
                                name,
                                email,
                                "default",
                                id
                        );

                        university.addUser(manager);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
}