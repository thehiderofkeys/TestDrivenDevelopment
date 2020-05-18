import java.time.LocalDateTime;
import java.util.ArrayList;

@CoverageIgnore
public class Course {

    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private String courseName;
    private ArrayList<TimetableEvent> courseSchedule;


    @CoverageIgnore
    public LocalDateTime getOpeningDate(){
        return this.openingDate;
    }
    @CoverageIgnore
    public LocalDateTime getClosingDate(){
        return this.closingDate;
    }
    @CoverageIgnore
    public String getCourseName(){
        return this.courseName;
    }
    @CoverageIgnore
    public ArrayList<TimetableEvent> getCourseSchedule(){
        return courseSchedule;
    }
}
