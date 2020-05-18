import java.util.ArrayList;
import java.util.HashMap;

public class EnrollmentDatabase {
    private HashMap<String,ArrayList<Course>> database = new HashMap<>();

    public void addEnrollment(String username, ArrayList<Course> enrollmentList){
        if (!database.containsKey(username)){
            database.put(username, new ArrayList<>());
        }
        ArrayList<Course> list = database.get(username);
        list.addAll(enrollmentList);
    }
    public ArrayList<Course> getEnrolledCourses(String username){
        return database.get(username);
    }
}
