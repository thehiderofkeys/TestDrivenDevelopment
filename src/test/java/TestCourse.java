import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

@Category(UnitTest.class)
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

    @Test
    public void Should_LimitSeats_WhenCourseIsFull(){
        Course c1 = new Course("Name",null,null,null, 2);
        assertTrue(c1.reserveSeat());
        assertTrue(c1.reserveSeat());
        assertFalse(c1.reserveSeat());
    }
    @Test
    public void Should_FreeSeat_WhenSeatReleased(){
        Course c1 = new Course("Name",null,null,null, 2);
        assertTrue(c1.reserveSeat());
        assertTrue(c1.reserveSeat());
        assertFalse(c1.reserveSeat());
        c1.releaseSeat();
        assertTrue(c1.reserveSeat());
    }

    @Test
    public void Should_QueueWaitList_WhenCourseIsFull(){
        Course c1 = new Course("Name",null,null,null);
        EnrollmentRequest request1 = Mockito.mock(EnrollmentRequest.class);
        EnrollmentRequest request2 = Mockito.mock(EnrollmentRequest.class);
        c1.addToWaitList(request1);
        c1.addToWaitList(request2);
        RequestObject result;
        result = c1.popWaitList();
        assertEquals(request1,result);
        result = c1.popWaitList();
        assertEquals(request2,result);
        result = c1.popWaitList();
        assertNull(result);
    }
}
