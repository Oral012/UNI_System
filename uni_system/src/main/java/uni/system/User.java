package uni.system;
public class User {
    private String name;
    private String email;
    private String password;
    private String role;
    private String userId;

    User (String name, String email, String password, String role, String userId){
        setName(name);
        setEmail(email);
        setPassword(password);
        setRole(role);
        setUserId(userId);
    }

    public String getName() {
        return name;
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
    public void setUserId( String userId){
        if( userId.isBlank()){
            throw new IllegalArgumentException("ID must not be blank.");
        }
        this.userId = userId;
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
    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + ", userId="
                + userId + "]";
    }
    
}
