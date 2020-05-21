import java.util.ArrayList;
import java.util.HashMap;

public class PrerequisitesVerifier {

    public ArrayList<Course> checkPrerequisites(ArrayList<Course> selectedCourses, String username,
                                                EnrollmentDatabase enrollmentDatabase){
        // courses will be removed from unacceptedCourses if they meet the prerequisites
        ArrayList<Course> unacceptedCourses = new ArrayList<>(selectedCourses);
        // check database to retrieve list of completed courses for user
        ArrayList<Course> completedCourses = enrollmentDatabase.getCompletedCourses(username);

        // for each selected course, retrieve a list of prerequisite courses
        for(Course selectedCourse : selectedCourses){
            ArrayList<String> preRequisiteCourses = selectedCourse.getPrerequisites();
            ArrayList<String> preRequisiteCoursesCopy = new ArrayList<>(preRequisiteCourses);



            // for each prerequisite course, check each completed course of the user for a match
            for(String prereq : preRequisiteCourses){
                for(Course completedCourse : completedCourses){
                    if(completedCourse.getCourseName().equals(prereq)) {
                        preRequisiteCoursesCopy.remove(prereq);
                    }
                }
            }

            if (preRequisiteCoursesCopy.isEmpty()) {
                unacceptedCourses.remove(selectedCourse);
            }
        }
        return unacceptedCourses;
    }
}
