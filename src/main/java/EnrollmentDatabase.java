import java.util.ArrayList;
import java.util.HashMap;

public class EnrollmentDatabase {
    private HashMap<String,ArrayList<Course>> database = new HashMap<>();

    public void addEnrollment(String username, ArrayList<Course> enrollmentList){
        database.put(username,enrollmentList);
    }
    public ArrayList<Course> getEnrolledCourses(String username){
        return database.get(username);
    }
}
