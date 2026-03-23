package uni.system.action;

import java.util.ArrayList;


import uni.system.model.Enrollment;


public class EnrollmentStudent {
    ArrayList<Enrollment> enrollments;
    public EnrollmentStudent(){
        this.enrollments = new ArrayList<>();
    }
    public void enrollStudent(Enrollment enrollment){
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(enrollment.getStudent()) &&
                e.getCourse().equals(enrollment.getCourse()) &&
                e.getStatus() == enrollment.getStatus() && e.getEnrollYear() == (enrollment.getEnrollYear())) {
                throw new IllegalStateException("Student already enrolled in this course");
            }
        }
        enrollments.add(enrollment);
    }
     public void enrollStudent(ArrayList<Enrollment> enrollmentList) {
        for (Enrollment enrollment : enrollments) {
            enrollStudent(enrollment);
        }
    }

    public void removeStudent( Enrollment enrollment){
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
