package uni.system.model;
public class User {
    private String name;
    private String email;
    private String password;
    private String role;
    private  static int userId;
    private String status;

    public User (String name, String email, String password, String role, String status){
        setName(name);
        setEmail(email);
        setPassword(password);
        setRole(role);
        setStatus(status);
        userId++;
    }

    public String getName() {
        return name;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
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
    public void setRole( String role){
        if( role.isBlank()){
            throw new IllegalArgumentException("Role must not be blank.");
        }
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Name =" + name + "\n" +"Email = "+ email +"\n"+ "Password = " + password + "\n"+ "Role= " 
        + role +"\n"+ "UserId = " + userId;
    }
    
}
