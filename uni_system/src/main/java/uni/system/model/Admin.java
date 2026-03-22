package uni.system.model;


public class Admin extends User  {
    public Admin ( String name, String email, String password, String adminId){
        super(name, email, password, Role.ADMIN);
    }

    
    public Role getRole ( ) {
        return  super.getRole();
    }
    
    @Override
    public void viewProfile() {
     // TODO Auto-generated method stub
     System.out.println("Name = " + getName() + "\n" 
        + "Email = " + getEmail() + "\n"
        + "Role = " + getRole() );         
    }
    
}
