package uni.system.model;
import java.util.ArrayList;

public class Department {
    private String departmentName;
    private String departmentCode;
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    public Department(String departmentName, String departmentCode) {
        setDepartmentName(departmentName);
        setDepartmentCode(departmentCode);
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }
    public void setDepartmentName(String departmentName) {
        if(departmentName.isBlank()){
            throw new IllegalArgumentException("Department name must not be blank.");
        }
        this.departmentName = departmentName;
    }
    public void setDepartmentCode(String departmentCode) {
        if(departmentCode.isBlank()){
            throw new IllegalArgumentException("Department code must not be blank.");
        }
        this.departmentCode = departmentCode;
    }
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public String getDepartmentCode() {
        return departmentCode;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }


    
}
