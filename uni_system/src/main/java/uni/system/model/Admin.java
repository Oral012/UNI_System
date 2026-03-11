package uni.system.model;


public class Admin extends User  {
    
    public Admin ( String name, String email, String password, String role, String status ){
        super(name, email, password, Role.ADMIN);
    }
    
    public Role getRole ( ) {
        return  super.getRole();
    }
    
    @Override
    public void viewProfile ( ) {
        // Implementation of viewProfile method
    }
    
}
