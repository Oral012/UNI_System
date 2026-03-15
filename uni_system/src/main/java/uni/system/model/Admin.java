package uni.system.model;

public class Admin extends User {
    
    public Admin ( String name, String email, String password, String role, String status ){
        super(name, email, password);
    }
    
    public String getRole ( ) {
        return "Admin";
    }
    @Override
    public void viewProfile() {
     // TODO Auto-generated method stub
     System.out.println("Name = " + getName() + "\n" 
        + "Email = " + getEmail() + "\n"
        + "Role = " + getRole() );         
    }
    
}
