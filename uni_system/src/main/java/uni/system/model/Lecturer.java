package uni.system.model;

import java.util.ArrayList;
import java.util.List;
import uni.system.action.EnrollmentStudent;
import uni.system.action.Gradeable;

public class Lecturer extends User implements Gradeable {
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

public void viewCourseGrades(EnrollmentStudent enrollList) {
    System.out.println("===== Student Grades =====");

    // Stop if this lecturer has no courses
    if (assignedCourses.isEmpty()) {
        System.out.println("No courses assigned.");
        return;
    }

    // Loop through each course assigned to this lecturer
    for (Course course : assignedCourses) {
        System.out.println("[ " + course.getCourseId() + " - " + course.getCourseName() + " ]");
        boolean hasStudents = false;
        // Loop through all enrollments to find students in this course
        for (Enrollment enrollment : enrollList.getList()) {
            // Check if this enrollment belongs to the current course
            boolean isSameCourse = enrollment.getCourse().getCourseId().equals(course.getCourseId());
            if (isSameCourse) {
                hasStudents = true;
                // Get the student and their grade for this course
                Student student = enrollment.getStudent();
                double grade = student.getGrade(course);

                // If grade is negative, it means no grade has been given yet
                String gradeDisplay;
                if (grade >= 0) {
                    gradeDisplay = String.valueOf(grade);
                } else {
                    gradeDisplay = "Not graded yet";
                }

                System.out.println("   " + student.getName() + " : " + gradeDisplay);
            }
        }

        // If no students were found enrolled in this course
        if (!hasStudents) {
            System.out.println("   No students enrolled.");
        }
    }
}
    // Gradeable implementation
    @Override
    public void assignGrade(Student student, Course course, double grade) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        // Check the course that lecturer responsible for 
        boolean isMyCourse = false;
        for (Course c : assignedCourses) {
            if (c.getCourseId().equals(course.getCourseId())) {
                isMyCourse = true;
                break;
            }
        }
        if (!isMyCourse) {
            throw new IllegalArgumentException("You can only grade students in your own courses.");
        }
        // Check student who enrolled in lecturer class 
        boolean studentEnrolled = false;
        for (Enrollment e : student.getEnrollments()) {
            if (e.getCourse().getCourseId().equals(course.getCourseId())) {
                studentEnrolled = true;
                break;
            }
        }
        if (!studentEnrolled) {
            throw new IllegalArgumentException(student.getName() + " is not enrolled in " + course.getCourseId());
        }
        student.setGrade(course, grade);
        System.out.println("Grade recorded: " + student.getName() + " | " + course.getCourseId() + " | " + grade);
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
        viewCourses();
    }
    @Override
    public String toString() {
        return "Lecturer[" + lecturerId + "] " + getName() + " | Department: " + department.getDepartmentCode() + " | Specialization: " + specialization;
    }
}