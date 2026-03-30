package uni.system.action;

import java.util.ArrayList;

import uni.system.model.Course;
import uni.system.model.*;

public interface Gradeable {
    void assignGrade(Student student, Course course, double grade);
    
}
