import java.sql.Time;
import java.util.ArrayList;

public class ClashVerifier {

    public ArrayList<Course> checkClash(ArrayList<Course> courseList){
        ArrayList<Course> returnedCourseList = new ArrayList<>();
        Course course;
        Course course1;

        for (int i=0;i<courseList.size();i++){
            for (int x=i+1;x<courseList.size();x++){
                course = courseList.get(i);
                course1 = courseList.get(x);

                if(checkTwoCoursesForClash(course, course1)){
                    if (!returnedCourseList.contains(course)){
                        returnedCourseList.add(course);
                    }
                    if (!returnedCourseList.contains(course1)){
                        returnedCourseList.add(course1);
                    }
                }
            }
        }
        return returnedCourseList;
    }

    private boolean checkTwoCoursesForClash(Course course, Course course1) {
        for (TimetableEvent t : course.getCourseSchedule()){
            for (TimetableEvent t1 : course1.getCourseSchedule()){
                if (t.getStartTime().plusHours(t.getDuration()).isAfter(t1.getStartTime()) && (t.getStartTime().isBefore(t1.getStartTime()))){
                    return true;
                }
                if (t1.getStartTime().plusHours(t1.getDuration()).isAfter(t.getStartTime()) && (t1.getStartTime().isBefore(t.getStartTime()))){
                    return true;
                }
                if(t.getStartTime().isEqual(t1.getStartTime())){
                    return true;
                }
            }
        }
        return false;
    }
}
