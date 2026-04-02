package uni.system.model;

import java.util.ArrayList;

public class Admin extends User {
    private String adminId;

    // Constructor
    public Admin(String name, String email, String password, String adminId) {
        super(name, email, password, Role.ADMIN);
        setAdminId(adminId);
    }

    // Getter
    public String getAdminId() {
        return adminId;
    }

    // Setter
    public void setAdminId(String adminId) {
        if (adminId == null || adminId.isBlank()) {
            throw new IllegalArgumentException("Admin ID must not be blank.");
        }
        this.adminId = adminId;
    }

    @Override
    public void viewProfile() {
        System.out.println("===== Admin Profile =====");
        System.out.println("Name     : " + getName());
        System.out.println("Email    : " + getEmail());
        System.out.println("Admin ID : " + adminId);
        System.out.println("Role     : " + getRole());
    }

    // Add course and avoid duplicated course
    public void addCourse(ArrayList<Course> courses, Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        for (Course c : courses) {
            if (c.getCourseId().equals(course.getCourseId()))
                throw new IllegalArgumentException("Course already exists: " + course.getCourseId());
        }
        courses.add(course);
        System.out.println("Course [" + course.getCourseId() + "] added.");
    }

    // Remove Course
    public void removeCourse(ArrayList<Course> courses, String courseId) {
        if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("Course ID must not be blank.");
        }
        Course target = null;
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                target = c;
                break;
            }
        }
        if (target == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        courses.remove(target);
        System.out.println("Course [" + courseId + "] removed.");
    }

    // View all courses
    public void viewAllCourses(ArrayList<Course> courses) {
        System.out.println("===== All Courses =====");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course c : courses) {
            System.out.println("- " + c.getCourseId() + " | " + c.getCourseName());
        }
    }

    // Add new Student
    public void addStudent(ArrayList<Student> students, Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        for (Student s : students) {
            if (s.getStudentId().equals(student.getStudentId()))
                throw new IllegalArgumentException("Student already exists: " + student.getStudentId());
        }
        students.add(student);
        System.out.println("Student [" + student.getStudentId() + "] added.");
    }

    // Remove Student
    public void removeStudent(ArrayList<Student> students, String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID must not be blank.");
        }
        Student target = null;
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                target = s;
                break;
            }
        }
        if (target == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }
        students.remove(target);
        System.out.println("Student [" + studentId + "] removed.");
    }

    // Assign course to lecturer
    public void assignCourseToLecturer(Lecturer lecturer, Course course) {
        if (lecturer == null) {
            throw new IllegalArgumentException("Lecturer cannot be null.");
        }
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        lecturer.addAssignedCourse(course);
        System.out.println("Admin assigned [" + course.getCourseId() + "] to " + lecturer.getName());
    }

    // Remove course from lecturer
    public void removeCourseFromLecturer(Lecturer lecturer, String courseId) {
        if (lecturer == null) {
            throw new IllegalArgumentException("Lecturer cannot be null.");
        }
        if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("Course ID must not be blank.");
        }
        lecturer.removeAssignedCourse(courseId);
        System.out.println("Admin removed [" + courseId + "] from " + lecturer.getName());
    }

    // Set grade to student
   // @Override
    //public void assignGrade(Student student, Course course, double grade) {
    //     if (student == null) {
    //         throw new IllegalArgumentException("Student cannot be null.");
    //     }
    //     if (course == null) {
    //         throw new IllegalArgumentException("Course cannot be null.");
    //     }
    //     if (grade < 0 || grade > 100) {
    //         throw new IllegalArgumentException("Grade must be between 0 and 100.");
    //     }
    //     student.setGrade(course, grade); 
    //     System.out.println("Admin " + getName() + " recorded: " + student.getName() + " | " + course.getCourseId() + " | " + grade);
    // }
}