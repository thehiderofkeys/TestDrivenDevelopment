

import java.time.LocalDateTime;
import java.util.ArrayList;

@CoverageIgnore
public class Course {

    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private String courseName;
    private ArrayList<TimetableEvent> courseSchedule;
    private int remainingSeats = 0;

    public Course (String courseName, LocalDateTime openingDate, LocalDateTime closingDate,  ArrayList<TimetableEvent> courseSchedule){
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.courseName = courseName;
        this.courseSchedule = courseSchedule;
    }

    public Course(String courseName, LocalDateTime openingDate, LocalDateTime closingDate,  ArrayList<TimetableEvent> courseSchedule, int remainingSeats) {
        this(courseName, openingDate, closingDate, courseSchedule);
        this.remainingSeats = remainingSeats;
    }


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

    public boolean reserveSeat() {
        if (remainingSeats > 0){
            remainingSeats--;
            return true;
        }
        return false;
    }

    public void releaseSeat() {
        remainingSeats++;
    }

    public void addToWaitList(RequestObject request) {
    }

    public RequestObject popWaitList() {
        return null;
    }
}
