import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestClashVerifier {

    private ClashVerifier verifier;
    private HashMap<String, Course> courseDatabase;

    @Before
    public void setUp() {
        verifier = new ClashVerifier();
        courseDatabase = new HashMap<>();

        TimetableEvent lecture1 = TimetableEvent("2014-03-28T16:00:00.000", 2);
        TimetableEvent lecture2 = TimetableEvent("2014-03-28T16:00:00.000", 2);
        TimetableEvent lecture3 = TimetableEvent("2014-03-28T18:00:00.000", 2);

        Course course1 = Mockito.mock(Course.class);
        Course course2 = Mockito.mock(Course.class);
        Course course3 = Mockito.mock(Course.class);

        Mockito.when(course1.getCourseSchedule()).thenReturn(new ArrayList<TimetableEvent>(lecture1));
        Mockito.when(course2.getCourseSchedule()).thenReturn(new ArrayList<TimetableEvent>(lecture2));
        Mockito.when(course3.getCourseSchedule()).thenReturn(new ArrayList<TimetableEvent>(lecture3));

        courseDatabase.put("SOFTENG 701", course1);
        courseDatabase.put("SOFTENG 702", course2);
        courseDatabase.put("SOFTENG 703", course3);
    }

    @Test
    public void Should_ReturnEmptyList_When_CourseTimesDontOverlap() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDatabase.get("SOFTENG 701"));
        courseList.add(courseDatabase.get("SOFTENG 703"));
        ArrayList<Course> clashList = verifier.checkClash(courseList);
        assertTrue(clashList.isEmpty());
    }
}
