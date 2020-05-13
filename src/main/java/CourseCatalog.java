import java.util.ArrayList;

public class CourseCatalog {

    private ArrayList<Course> courseCatalog = new ArrayList<>();

    public void add(Course course){
        courseCatalog.add(course);
    }
    public ArrayList getAllCourses(){
        return courseCatalog;
    }
}
