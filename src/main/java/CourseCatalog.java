import java.util.ArrayList;

public class CourseCatalog {

    private ArrayList<Course> courseCatalog = new ArrayList<>();
    private ArrayList<Course> searchResults = new ArrayList<>();

    public void add(Course course){
        courseCatalog.add(course);
    }
    public ArrayList<Course> getAllCourses(){
        return courseCatalog;
    }
    public ArrayList<Course> search(String userSearch){
        searchResults.clear();
        for (Course course : courseCatalog){
            if (course.getCourseName().equals(userSearch)){
                searchResults.add(course);
            }
        }
        return searchResults;
    }
}
