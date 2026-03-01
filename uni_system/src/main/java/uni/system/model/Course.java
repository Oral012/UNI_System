package uni.system.model;

public class Course {
    private String courseId;
    private String courseName;
    private double credit;
    private Department department;
    private int yearLevel;
    public Course (String courseId, String courseName, double credit, Department department, int yearLevel){
        setCourseId(courseId);
        setCourseName(courseName);
        setCredit(credit);
        setDepartment(department);
        setYearLevel(yearLevel);
    }
    public void setDepartment(Department department) {
        if(department == null){
            throw new IllegalArgumentException("Department must not be null.");
        }
        this.department = department;
    }
    public void setYearLevel(int yearLevel) {
        if(yearLevel < 1 || yearLevel > 4){
            throw new IllegalArgumentException("Year level must be between 1 and 4.");
        }
        this.yearLevel = yearLevel;
    }
    public int getYearLevel() {
        return yearLevel;
    }
    public Department getDepartment() {
        return department;
    }
    public void setCourseId(String courseId) {
        if( courseId.isBlank()){
            throw new IllegalArgumentException("Id can not be empty!.");
        }
        this.courseId = courseId;
    }
    public void setCourseName(String courseName) {
         if( courseName.isBlank()){
            throw new IllegalArgumentException("Name can not be empty!.");
        }
        this.courseName = courseName;
    }
    public void setCredit(double credit) {
        if( credit > 5.0 || credit <0){
            throw new IllegalArgumentException("Credit out of range of 0.0 to 5.0.");
        }
        this.credit = credit;
    }
public String getCourseId() {
    return courseId;
}
@Override
public String toString() {
    return "Course courseId=" + courseId + ", courseName=" + courseName + ", credit=" + credit;
}
public String getCourseName() {
    return courseName;
}
public double getCredit() {
    return credit;
}

}
