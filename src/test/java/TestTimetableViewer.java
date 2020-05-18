import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

public class TestTimetableViewer{
    private EnrollmentDateVerifier verifier;

    @Before
    public void setUp() {

    }

    @Test
    public void Should_returnTimeTableEventList_when_listOfCoursesGiven(){

        List<Course> givenCourseList = new ArrayList<Course>();

        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Course mockedCourse3 = Mockito.mock(Course.class);

//        TimetableEvent lecture1 = new TimetableEvent(LocalDateTime.parse("2014-03-28T16:00:00.000"));
//        TimetableEvent lecture2 = new TimetableEvent(LocalDateTime.parse("2014-03-28T16:00:00.000"));
//        TimetableEvent lecture3 = new TimetableEvent(LocalDateTime.parse("2014-03-28T20:00:00.000"));

        givenCourseList.add(mockedCourse1);
        givenCourseList.add(mockedCourse2);
        givenCourseList.add(mockedCourse3);

        List<TimetableEvent> timetableEventList = new ArrayList<>();


//        List<TimetableEvent> timetableEventList = new ArrayList<TimetableEvent>();
//        timetableEventList.add(lecture1);
//        timetableEventList.add(lecture2);
//        timetableEventList.add(lecture3);
        // should return a timetable event data list
        assertEquals((timetableEventList).size(), 3);
        assertTrue(timetableEventList.contains(mockedCourse1));
        assertTrue(timetableEventList.contains(mockedCourse2));
        assertTrue(timetableEventList.contains(mockedCourse3));
    }
}

