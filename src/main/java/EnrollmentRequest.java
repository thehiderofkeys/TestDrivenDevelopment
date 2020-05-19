import java.util.ArrayList;

public class EnrollmentRequest implements RequestObject{
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

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof EnrollmentRequest))
            return false;
        EnrollmentRequest er = (EnrollmentRequest)o;
        return username.equals(er.getUsername()) && courses.equals(er.courses);
    }
}
