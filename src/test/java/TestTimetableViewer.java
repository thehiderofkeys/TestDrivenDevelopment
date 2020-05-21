import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

@Category(UnitTest.class)
public class TestTimetableViewer{
    private TimetableViewer timetableViewer;

    @Before
    public void setUp() {
        timetableViewer = new TimetableViewer();
    }

    @Test
    public void Should_returnTimeTableEventList_when_listOfCoursesGiven(){

        List<Course> givenCourseList = new ArrayList<Course>();

        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Course mockedCourse3 = Mockito.mock(Course.class);

        givenCourseList.add(mockedCourse1);
        givenCourseList.add(mockedCourse2);
        givenCourseList.add(mockedCourse3);

        TimetableEvent lecture1 = Mockito.mock(TimetableEvent.class);
        TimetableEvent lecture2 = Mockito.mock(TimetableEvent.class);
        TimetableEvent lecture3 = Mockito.mock(TimetableEvent.class);

        ArrayList<TimetableEvent> courseSchedule1 = new ArrayList<>();
        courseSchedule1.add(lecture1);
        courseSchedule1.add(lecture2);
        ArrayList<TimetableEvent> courseSchedule2 = new ArrayList<>();
        courseSchedule2.add(lecture1);
        courseSchedule2.add(lecture3);
        ArrayList<TimetableEvent> courseSchedule3 = new ArrayList<>();
        courseSchedule3.add(lecture2);
        courseSchedule3.add(lecture3);

        Mockito.when(mockedCourse1.getCourseSchedule())
                .thenReturn(courseSchedule1);
        Mockito.when(mockedCourse2.getCourseSchedule())
                .thenReturn(courseSchedule2);
        Mockito.when(mockedCourse3.getCourseSchedule())
                .thenReturn(courseSchedule3);

        List<TimetableEvent> combinedTimetableEventList = timetableViewer.getAllTimetableEvents(givenCourseList);

        assertEquals((combinedTimetableEventList).size(), 6);
        assertTrue(combinedTimetableEventList.contains(lecture1));
        assertTrue(combinedTimetableEventList.contains(lecture2));
        assertTrue(combinedTimetableEventList.contains(lecture3));

    }
}

