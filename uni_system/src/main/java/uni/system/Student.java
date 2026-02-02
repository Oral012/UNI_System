package uni.system;
public class  Student extends User {
    private String major;
    private double gpa;
    
    public Student (String name, String email, String password, String role, String userId, String major, double gpa){
        super( name, email, password, role, userId);    //use constructor of Parent class ( User class)
        setGpa(gpa);
        setMajor(major);
        
    }
    public String getMajor() {
        return major;
    }
    public double getGpa() {
        return gpa;
    }
    public String getRole ( ) {
        return "STUDENT";
    }

    public void setMajor(String major) {
        if(major.isBlank()){
            throw new IllegalArgumentException("major must not be blank.");
        }
        this.major = major;
    }
    public void setGpa(double gpa) {    
        if( gpa < 0 || gpa > 4 ){
            throw new IllegalArgumentException("GPA is not correct.");
        }
        this.gpa = gpa;
    }
    @Override
    public String toString() {
        return "Student [major=" + major + ", gpa=" + gpa + "]";
    }
    
    
}
