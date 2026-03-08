package uni.system.model;
import java.util.ArrayList;
import uni.system.service.Enrollment;
public class  Student extends User {
    private Department department;
    private String major;
    private String studentId;
    private int yearLevel;
    private ArrayList<Enrollment> enrollment;
    public Student (String name, String email,String studentId, String password, Department department, String major, int yearLevel){
        super( name, email, password);    //use constructor of Parent class ( User class)
        setDepartment(department);
        setMajor(major);
        setYearLevel(yearLevel);
        setStudentId(studentId);
        enrollment = new ArrayList<>();
    }
    
    // Getters methods
    public int getYearLevel(){ return yearLevel;}
    public Department getDepartment(){ return department;}
    public String getMajor() {return major;}
    public String getStudentId() { return studentId; }
    public ArrayList<Enrollment> getEnrollment() { return enrollment;}
    
    // Setters methods
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
    public void setMajor(String major) {
        if(major.isBlank()) throw new IllegalArgumentException("major must not be blank.");
        this.major = major;
    }
    public void setStudentId(String studentId) {
        if (studentId.isBlank()) throw new IllegalArgumentException("Student ID error");
        this.studentId = studentId;
    }
    public void setEnrollment(ArrayList<Enrollment> enrollment) {
        if( enrollment.isEmpty()) throw new IllegalArgumentException("list is empty");
        this.enrollment = enrollment;
    }

   
    public void addEnrollment( Enrollment enrollment){
        if ( !enrollment.equals(null)){
            this.enrollment.add(enrollment);
        }
    }
    public void removeEnrollment( Enrollment enrollment){
        if (!enrollment.equals(null)){
            this.enrollment.remove(enrollment);
        }
    }
    @Override
    public void viewProfile() {
        // TODO Auto-generated method stub
        System.out.println("Name = " + getName() + "\n" +"Email = "+ getEmail() +"\n"+ "Department = " + department.getDepartmentName() + "\n"
         + "Major = " + major + "\n" + "Year Level = " + yearLevel);
        
    }
    public void printInfo(){
        System.out.println("Name = " + getName() + "\n" +"Email = "+ getEmail() +"\n");
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
