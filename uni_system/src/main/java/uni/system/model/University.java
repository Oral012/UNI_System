package uni.system.model;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private String address;
    ArrayList<Department> departments;
    ArrayList<Course> courses;
    List<User> users;
    public University( String name, String address){
        if (name.isBlank()) throw new IllegalArgumentException("University name must not be blank.");
        if (address.isBlank()) throw new IllegalArgumentException("University address must not be blank.");
        this.name = name;
        this.address = address;
        departments = new ArrayList<>();
        courses = new ArrayList<>();
        users = new ArrayList<>();
    }
    //getter methods
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public ArrayList<Department> getDepartments() {
        return departments;
    }
    public List<User> getUsers() {
        return users;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    //methods
    public void addDepartment(Department department){
        departments.add(department);
    }
    public void addUser( User user){
        for (User u : getUsers()) {
            if (u.getEmail().equals(user.getEmail())){
                throw new IllegalArgumentException("Email already exists. Try another email");
            }
        }
        users.add(user);
    }
    public void deleteUser(User user){
        users.remove(user);
    }
    public void addListOfUsers(List <User> users){
        for ( User user : users){
            addUser(user);
        }
    }
    public void printAllUsers(){
        for ( User user : users){
            System.out.println(user.getName() + "\t\t" + user.getRole());
        }
    }
    public User login( String name, String password){
        for ( User user : users){
            if ( user.getName().equalsIgnoreCase(name) && user.getPassword().equalsIgnoreCase(password)){
                System.out.println("Login successful. Welcome, " + user.getName() + "!");
                return user;
            }
            }
            System.out.println("Invalid name or password. Pls try again.");
            return null;
        } 
        public void addCourse( Course course) {
            courses.add(course);
        }
        public void deleteCourse( Course course) {
            courses.remove( course);
        }
}
    
