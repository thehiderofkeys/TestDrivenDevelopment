import java.time.LocalDateTime;

@CoverageIgnore
public class Course {

    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private String courseName;


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
}
