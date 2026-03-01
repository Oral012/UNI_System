package uni.system.model;
import java.util.ArrayList;
import uni.system.service.Enrollment;
public class  Student extends User {
    private Department department;
    private String major;
    private static int studentId;
    private int yearLevel;
    ArrayList<Enrollment> enrollment;
    public Student (String name, String email, String password, Department department, String major, int yearLevel){
        super( name, email, password);    //use constructor of Parent class ( User class)
        setDepartment(department);
        setMajor(major);
        setYearLevel(yearLevel);
        studentId++;
        enrollment = new ArrayList<>();
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
    public int getYearLevel(){
        return yearLevel;
    }
    public Department getDepartment(){
        return department;
    }
    public String getMajor() {
        return major;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setMajor(String major) {
        if(major.isBlank()){
            throw new IllegalArgumentException("major must not be blank.");
        }
        this.major = major;
    }
    public String encryptPassword(){
        String password = getPassword();
        String encrypt = "";
        for( int i = 0; i< password.length(); i++){
            encrypt += "*";
        }
        return encrypt;
    }

    public void printInfo(){
        System.out.println("Name = " + getName() + "\n" +"Email = "+ getEmail() +"\n"+ "Password = " + encryptPassword() +"\n");
    }
    
    
}
