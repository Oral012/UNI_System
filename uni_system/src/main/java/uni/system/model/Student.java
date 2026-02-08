package uni.system.model;


public class  Student extends User {
    private String major;
    private double gpa;
    
    public Student (String name, String email, String password, String role, String status,  String major, double gpa){
        super( name, email, password, role, status);    //use constructor of Parent class ( User class)
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
    public String encryptPassword(){
        String password = getPassword();
        String encrypt = "";
        for( int i = 0; i< password.length(); i++){
            encrypt += "*";
        }
        return encrypt;
    }
    @Override
    public String toString() {
        return "major=" + major + ", gpa=" + gpa;
    }
    public void printInfo(){
        System.out.println("Name = " + getName() + "\n" +"Email = "+ getEmail() +"\n"+ "Password = " + encryptPassword() + "\n"+ "Role= " 
        + getRole() +"\n" +"UserId = IDTB" + getUserId());
    }
    
    
}
