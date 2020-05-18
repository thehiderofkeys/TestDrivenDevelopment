import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestEnrollmentDatabase {
    private EnrollmentDatabase enrollmentDatabase;

    @Before
    public void setUp() {
        enrollmentDatabase = new EnrollmentDatabase();
    }

    @Test
    public void Should_PersistEnrollment_When_EnrollmentRequested(){
        ArrayList<Course> desiredCourses = new ArrayList<>();
        Course course1 = Mockito.mock(Course.class);
        desiredCourses.add(course1);
        Course course2 = Mockito.mock(Course.class);
        desiredCourses.add(course2);
        Course course3 = Mockito.mock(Course.class);
        desiredCourses.add(course3);
        enrollmentDatabase.addEnrollment("usr123",desiredCourses);
        ArrayList<Course> result = enrollmentDatabase.getEnrolledCourses("usr123");
        assertNotEquals(null,result);
        assertEquals(3, result.size());
        assertTrue(result.contains(course1) & result.contains(course2) & result.contains(course3));
    }
}
