import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimetableViewer {
    private List<TimetableEvent> combinedTimetableEventList = new ArrayList<>();

    public List<TimetableEvent> getAllTimetableEvents(List<Course> givenCourseList){
        for (Course course : givenCourseList){
            List<TimetableEvent> timetableEventList = course.getCourseSchedule();
            for (TimetableEvent timetableEvent : timetableEventList){
                combinedTimetableEventList.add(timetableEvent);
            }
        }
        return combinedTimetableEventList;
    }
}
