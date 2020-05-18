import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestCourse {

    private HashMap<String, Course> courseDatabase;

    @Before
    public void setUp(){
        courseDatabase = new HashMap<>();
    }

    @Test
    public void should_ReturnCourseDetails_when_CourseInfoRequested(){
        ArrayList<TimetableEvent> courseSchedule = new ArrayList<TimetableEvent>();
        TimetableEvent lec = new TimetableEvent(LocalDateTime.parse("2014-04-28T16:00:00.00"), 1, EventType.Lecture);
        TimetableEvent tut = new TimetableEvent(LocalDateTime.parse("2014-04-29T16:00:00.00"), 1, EventType.Tutorial);
        TimetableEvent lab = new TimetableEvent(LocalDateTime.parse("2014-04-40T16:00:00.00"), 1, EventType.Lab);

        courseSchedule.add(lec);
        courseSchedule.add(tut);
        courseSchedule.add(lab);


        ArrayList<Person> teachingStaff = new ArrayList<Person>();
        Person p1 = new Person("Sam Neale", {CourseRole.Lecturer});
        Person p2 = new Person("Ryan Lim", {CourseRole.Lecturer, CourseRole.CourseCoordinator});
        teachingStaff.add(p1);
        teachingStaff.add(p2);


        Course c1 = new Course("SOFTENG 754", (LocalDateTime.parse("2014-03-28T16:00:00.00")), (LocalDateTime.parse("2014-03-28T16:00:00.00")), courseSchedule);
        courseDatabase.put("SOFTENG 754" , c1);

        // "Fake" course request
        Course returnedCourse = courseDatabase.get("SOFTENG 701");

        assertEquals(c1, returnedCourse);
    }
}
