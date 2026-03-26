import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        do {
       String name = sc.next();
       
       if ( name.matches("^[a-zA-Z._]{3,15}$")) {
           System.out.println("name is valid");
        } else {
            System.out.println("name is Invalid");
        }
        String email = sc.next();
       if ( email.matches( "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$")){
        System.out.println("email is valid");
       } else System.out.println("email is invalid");
    } while( true);
}
}
