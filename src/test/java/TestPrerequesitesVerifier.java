import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertTrue;

public class TestPrerequesitesVerifier {

    private PrerequisitesVerifier verifier;
    private HashMap<String, Course> courseDatabase;

    @Before
    public void setUp() {
        verifier = new PrerequisitesVerifier();
        courseDatabase = new HashMap<>();

        Course course1 = Mockito.mock(Course.class);
        Course course2 = Mockito.mock(Course.class);
        Course course3 = Mockito.mock(Course.class);

        String prereqs1[] = {"SOFTENG701"};
        String prereqs2[] = {"SOFTENG701", "SOFTENG702"};

        Mockito.when(course2.getPrerequisites()).thenReturn(new ArrayList<String>(Arrays.asList(prereqs1)));
        Mockito.when(course3.getPrerequisites()).thenReturn(new ArrayList<String>(Arrays.asList(prereqs2)));

        courseDatabase.put("SOFTENG 701", course1);
        courseDatabase.put("SOFTENG 702", course2);
        courseDatabase.put("SOFTENG 703", course3);
    }

    @Test
    public void Should_Return_InvalidCourses_When_CourseSelectionGiven() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDatabase.get("SOFTENG 701"));
        courseList.add(courseDatabase.get("SOFTENG 703"));
        ArrayList<Course> rejectedCourseList = verifier.checkPrerequisites(courseList);
        assertTrue(rejectedCourseList.isEmpty());
    }
}
