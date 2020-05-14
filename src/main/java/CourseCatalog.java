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
        if (userSearch.length() == 0) {
            return searchResults;
        }
        for (Course course : courseCatalog){
            if (course.getCourseName().contains(userSearch)){
                searchResults.add(course);
            }
        }
        return searchResults;
    }
}
