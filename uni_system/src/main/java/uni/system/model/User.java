package uni.system.model;

public abstract class User { // need to implement later
    private String name;
    private String email;
    private String password;
    private Role role;
    public User (String name, String email, String password, Role role){
        
        setName(name);
        setEmail(email);
        setPassword(password);
        this.role = role;
    }
    public void setName( String name){
        if( name.isBlank()){
            throw new IllegalArgumentException("Name must not be blank.");
        }
        this.name = name;
    }
    public void setEmail( String email){
        if( email.isBlank() || !email.contains("@")){
            throw new IllegalArgumentException("Email must not be blank or missing @.");
        }
        this.email = email;
    }
    public void setPassword(String password){
        if( password.isBlank()){
            throw new IllegalArgumentException("Password must not be blank.");
        }
        this.password = password;
    }
    // Getters methods
    public String getName() { return name;}
    public String getEmail() { return email;}
    public Role getRole () {return role;}
    //public String getPassword() {return password;}

    @Override
    public String toString() {
        return "Name =" + name + "\n" +"Email = "+ email +"\n"+ "Password = " + password +"\n";
    }


    public  abstract void viewProfile();


    
    
}
