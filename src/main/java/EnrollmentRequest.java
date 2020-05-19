import java.util.ArrayList;

public class EnrollmentRequest {
    private String username;
    private ArrayList<Course> courses;
    public EnrollmentRequest(String username, ArrayList<Course> courses) {
        this.username = username;
        this.courses = courses;
    }
    public String getUsername(){
        return username;
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }
}
