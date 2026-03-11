package uni.system.action;

import java.util.ArrayList;

import uni.system.model.Enrollment;

public class EnrollmentStudent {
    ArrayList<Enrollment> enrollments;
    public EnrollmentStudent( Enrollment enrollment){
        this.enrollments = new ArrayList<>();
        enrollments.add(enrollment);
    }
    public void addStudent( Enrollment enrollment){
        enrollments.add(enrollment);
    }
    public void deleteStudent( Enrollment enrollment){
        int index = enrollments.indexOf( enrollment);
        if( index != -1){
            enrollments.remove(index);
        } else {
            System.out.println("Enrollment not found.");
        }
        
    }
    public void printEnrollment(){
        for( int i = 0; i< enrollments.size(); i++){
            System.out.println( i+1 + ". " + enrollments.get(i));
        }
    }
    public void printIndividual( int index){
        index--;
        if( index >=0 && index < enrollments.size()){
            enrollments.get(index).printInfo();
        } else {
            System.out.println("Index out of range.");
        }
    }
}
