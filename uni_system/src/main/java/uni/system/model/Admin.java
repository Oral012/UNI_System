package uni.system.model;

import uni.system.repo.Iuser;

public class Admin extends User implements Iuser {
    
    public Admin ( String name, String email, String password, String role, String status ){
        super(name, email, password);
    }
    
    public String getRole ( ) {
        return "Admin";
    }
    
    @Override
    public void viewProfile ( ) {
        // Implementation of viewProfile method
    }
    
}
