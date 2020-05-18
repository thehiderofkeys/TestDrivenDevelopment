import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        TimetableEvent lec = new TimetableEvent(LocalDateTime.parse("2014-04-28T16:00:00.00"), 1, TimetableEvent.EventType.Lecture);
        TimetableEvent tut = new TimetableEvent(LocalDateTime.parse("2014-04-29T16:00:00.00"), 1, TimetableEvent.EventType.Tutorial);
        TimetableEvent lab = new TimetableEvent(LocalDateTime.parse("2014-04-30T16:00:00.00"), 1, TimetableEvent.EventType.Lab);

        courseSchedule.add(lec);
        courseSchedule.add(tut);
        courseSchedule.add(lab);


        ArrayList<Person> teachingStaff = new ArrayList<Person>();
        Person.CourseRole[] roles1 =  {Person.CourseRole.Lecturer};
        Person p1 = new Person("Sam Neale", new ArrayList<Person.CourseRole>(Arrays.<Person.CourseRole>asList(roles1)));

        Person.CourseRole[] roles2 =  {Person.CourseRole.Lecturer};
        Person p2 = new Person("Ryan Lim", new ArrayList<Person.CourseRole>(Arrays.<Person.CourseRole>asList(roles2)));

        teachingStaff.add(p1);
        teachingStaff.add(p2);


        Course c1 = new Course("SOFTENG 754", (LocalDateTime.parse("2014-03-28T16:00:00.00")), (LocalDateTime.parse("2014-03-28T16:00:00.00")), courseSchedule);
        courseDatabase.put("SOFTENG 754" , c1);

        // "Fake" course request
        Course returnedCourse = courseDatabase.get("SOFTENG 754");

        assertEquals(c1, returnedCourse);
    }
}