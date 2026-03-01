package uni.system.model;

import uni.system.repo.Iuser;

public class User implements Iuser{
    private String name;
    private String email;
    private String password;
    public User (String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
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
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "Name =" + name + "\n" +"Email = "+ email +"\n"+ "Password = " + password +"\n";
    }

    @Override
    public boolean login(String username, String password) {
        return this.name.equals(username) &&
               this.password.equals(password);
    }


    
    
}
