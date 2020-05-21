import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class TestClashVerifier {

    private ClashVerifier verifier;
    private HashMap<String, Course> courseDatabase;

    @Before
    public void setUp() {
        verifier = new ClashVerifier();
        courseDatabase = new HashMap<>();

        TimetableEvent[] lecture1 = {new TimetableEvent(LocalDateTime.parse("2014-03-28T16:00:00.000"), 2, TimetableEvent.EventType.Lecture)};
        TimetableEvent[] lecture2 = {new TimetableEvent(LocalDateTime.parse("2014-03-28T16:00:00.000"), 2, TimetableEvent.EventType.Lecture)};
        TimetableEvent[] lecture3 = {new TimetableEvent(LocalDateTime.parse("2014-03-28T20:00:00.000"), 2, TimetableEvent.EventType.Lecture)};

        Course course1 = Mockito.mock(Course.class);
        Course course2 = Mockito.mock(Course.class);
        Course course3 = Mockito.mock(Course.class);

        Mockito.when(course1.getCourseSchedule()).thenReturn(new ArrayList<TimetableEvent>(Arrays.asList(lecture1)));
        Mockito.when(course2.getCourseSchedule()).thenReturn(new ArrayList<TimetableEvent>(Arrays.asList(lecture2)));
        Mockito.when(course3.getCourseSchedule()).thenReturn(new ArrayList<TimetableEvent>(Arrays.asList(lecture3)));

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

    @Test
    public void Should_ReturnClashingCourseNames_When_CourseTimesOverlap(){
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDatabase.get("SOFTENG 701"));
        courseList.add(courseDatabase.get("SOFTENG 702"));
        ArrayList<Course> clashList = verifier.checkClash(courseList);

        ArrayList<Course> expectedList = new ArrayList<>();
        expectedList.add(courseDatabase.get("SOFTENG 701"));
        expectedList.add(courseDatabase.get("SOFTENG 702"));

        assertEquals(expectedList, clashList);
    }
}
