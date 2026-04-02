package uni.system.model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                writer.write(String.format("%-30s %-15s %-20s %-30s %-20s",
                        "Timestamp", "Role", "ID", "Name", "Email"));
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
            return String.format("[%s]  %-10s %-15s %-20s %-30s Dept: %-8s Major: %-15s Year: %d",
                    timestamp,
                    "STUDENT",
                    s.getStudentId(),
                    s.getName(),
                    s.getEmail(),
                    s.getDepartment().getDepartmentCode(),
                    s.getMajor(),
                    s.getYearLevel());

        } else if (user instanceof Lecturer l) {
            return String.format("[%s]  %-10s %-15s %-20s %-30s Dept: %-8s Spec: %s",
                    timestamp,
                    "LECTURER",
                    l.getLecturerId(),
                    l.getName(),
                    l.getEmail(),
                    l.getDepartment().getDepartmentCode(),
                    l.getSpecialization());

        } else if (user instanceof Manager m) {
            return String.format("[%s]  %-10s %-15s %-20s %s",
                    timestamp,
                    "MANAGER",
                    m.getManagerId(),
                    m.getName(),
                    m.getEmail());

        } else {
            return String.format("[%s]  %-10s %-15s %-20s %s",
                    timestamp,
                    user.getRole(),
                    "N/A",
                    user.getName(),
                    user.getEmail());
        }
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
}