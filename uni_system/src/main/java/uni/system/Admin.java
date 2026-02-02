package uni.system;
public class Admin extends User {
    
    public Admin ( String name, String email, String password, String role, String userId){
        super(name, email, password, role, userId);
    }
    
    public String getRole ( ) {
        return "Admin";
    }
    
}
