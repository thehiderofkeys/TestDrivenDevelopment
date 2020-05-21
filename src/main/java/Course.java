

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@CoverageIgnore
public class Course {

    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private String courseName;
    private ArrayList<TimetableEvent> courseSchedule;
    private int remainingSeats = 0;
    private LinkedList<RequestObject> waitList;
    private ArrayList<String> prerequisites;

    public Course (String courseName, LocalDateTime openingDate, LocalDateTime closingDate,  ArrayList<TimetableEvent> courseSchedule){
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.courseName = courseName;
        this.courseSchedule = courseSchedule;
        this.waitList = new LinkedList<>();
    }

    public Course(String courseName, LocalDateTime openingDate, LocalDateTime closingDate,  ArrayList<TimetableEvent> courseSchedule, int remainingSeats) {
        this(courseName, openingDate, closingDate, courseSchedule);
        this.remainingSeats = remainingSeats;
    }

    @CoverageIgnore
    public Course(String courseName, LocalDateTime openingDate, LocalDateTime closingDate,
                  ArrayList<TimetableEvent> courseSchedule, int remainingSeats, ArrayList<String> prerequisites) {
        this(courseName, openingDate, closingDate, courseSchedule, remainingSeats);
        this.prerequisites = prerequisites;
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
    @CoverageIgnore
    public ArrayList<String> getPrerequisites() { return prerequisites; }

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
        waitList.addLast(request);
    }

    public RequestObject popWaitList() {
        try {
            return waitList.removeFirst();
        }
        catch (NoSuchElementException e){
            return null;
        }
    }
}
