package uni.system.model;

public class Admin extends User {
    
    public Admin ( String name, String email, String password, String role, String status ){
        super(name, email, password, role, status);
    }
    
    public String getRole ( ) {
        return "Admin";
    }
    
}
