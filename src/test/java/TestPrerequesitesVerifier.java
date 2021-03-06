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
    private EnrollmentDatabase enrollmentDatabase;

    private Course course1;
    private Course course2;
    private Course course3;

    @Before
    public void setUp() {
        verifier = new PrerequisitesVerifier();
        courseDatabase = new HashMap<>();
        enrollmentDatabase = Mockito.mock(EnrollmentDatabase.class);

        course2 = Mockito.mock(Course.class);
        course1 = Mockito.mock(Course.class);
        course3 = Mockito.mock(Course.class);

        String prereqs1[] = {"SOFTENG701"};
        String prereqs2[] = {"SOFTENG701", "SOFTENG702", "SOFTENG703"};

        Course completedPapers1[] = {course1, course2};

        Mockito.when(course1.getCourseName()).thenReturn("SOFTENG701");
        Mockito.when(course2.getCourseName()).thenReturn("SOFTENG702");
        Mockito.when(course3.getCourseName()).thenReturn("SOFTENG703");

        Mockito.when(course1.getPrerequisites()).thenReturn(new ArrayList<String>(Arrays.asList(prereqs1)));
        Mockito.when(course2.getPrerequisites()).thenReturn(new ArrayList<String>(Arrays.asList(prereqs2)));
        Mockito.when(course3.getPrerequisites()).thenReturn(new ArrayList<String>(Arrays.asList(prereqs2)));


        courseDatabase.put("SOFTENG 701", course1);
        courseDatabase.put("SOFTENG 702", course2);
        courseDatabase.put("SOFTENG 703", course3);
    }

    @Test
    public void Should_Return_EmptyList_When_CourseSelectionMeetsPrerequisites() {
        Course completedPapers[] = {course1, course2};
        Mockito.when(enrollmentDatabase.getCompletedCourses("user123"))
                .thenReturn(new ArrayList<Course>(Arrays.asList(completedPapers)));

        ArrayList<Course> courseSelectionList = new ArrayList<>();
        courseSelectionList.add(course1);
        ArrayList<Course> rejectedCourseList = verifier.checkPrerequisites(courseSelectionList, "user123", enrollmentDatabase);
        assertTrue(rejectedCourseList.isEmpty());
    }

    @Test
    public void Should_Return_ListOfOneInvalidCourse_When_OneCourseSelectionMeetsPrerequisitesAndOneDoesNot() {
        Course completedPapers[] = {course1, course2};
        Mockito.when(enrollmentDatabase.getCompletedCourses("user123"))
                .thenReturn(new ArrayList<Course>(Arrays.asList(completedPapers)));

        ArrayList<Course> courseSelectionList = new ArrayList<>();
        courseSelectionList.add(course1);
        courseSelectionList.add(course3); // user123 does not meet the prereqs for this
        ArrayList<Course> rejectedCourseList = verifier.checkPrerequisites(courseSelectionList, "user123", enrollmentDatabase);
        assertTrue(rejectedCourseList.contains(course3));
        assertEquals(rejectedCourseList.size(), 1);
    }

    @Test
    public void Should_Return_ListOfTwoInvalidCoursesWhen_TwoCourseSelectionsDoNotMeetPrerequisites(){
        Course completedPapers[] = {course1};
        Mockito.when(enrollmentDatabase.getCompletedCourses("user123"))
                .thenReturn(new ArrayList<Course>(Arrays.asList(completedPapers)));

        ArrayList<Course> courseSelectionList = new ArrayList<>();
        courseSelectionList.add(course2);
        courseSelectionList.add(course3); // user123 does not meet the prereqs for this
        ArrayList<Course> rejectedCourseList = verifier.checkPrerequisites(courseSelectionList, "user123", enrollmentDatabase);

        assertTrue(rejectedCourseList.contains(course2));
        assertTrue(rejectedCourseList.contains(course3));
        assertEquals(rejectedCourseList.size(), 2);
    }
}
