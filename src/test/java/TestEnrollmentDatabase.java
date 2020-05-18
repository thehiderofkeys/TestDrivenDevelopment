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
    @Test
    public void Should_PersistEnrollment_When_MultipleEnrollmentRequested(){
        ArrayList<Course> desiredCourses1 = new ArrayList<>();
        ArrayList<Course> desiredCourses2 = new ArrayList<>();
        Course course1 = Mockito.mock(Course.class);
        desiredCourses1.add(course1);
        Course course2 = Mockito.mock(Course.class);
        desiredCourses2.add(course2);
        Course course3 = Mockito.mock(Course.class);
        desiredCourses2.add(course3);
        enrollmentDatabase.addEnrollment("usr123",desiredCourses1);
        enrollmentDatabase.addEnrollment("usr123",desiredCourses2);
        ArrayList<Course> result = enrollmentDatabase.getEnrolledCourses("usr123");
        assertNotEquals(null,result);
        assertEquals(3, result.size());
        assertTrue(result.contains(course1) & result.contains(course2) & result.contains(course3));
    }
    @Test
    public void Should_PersistConcessions_When_ConcessionRequested(){
        ArrayList<Concession> concessions = new ArrayList<>();
        Concession concession1 = Mockito.mock(Concession.class);
        concessions.add(concession1);
        Concession concession2 = Mockito.mock(Concession.class);
        concessions.add(concession2);
        Concession concession3 = Mockito.mock(Concession.class);
        concessions.add(concession3);
        enrollmentDatabase.addConcessions("usr123",concessions);
        ArrayList<Concession> result = enrollmentDatabase.getConcessions("usr123");
        assertNotEquals(null,result);
        assertEquals(3, result.size());
        assertTrue(result.contains(concession1) & result.contains(concession2) & result.contains(concession3));
    }
    @Test
    public void Should_PersistConcessions_When_MultipleConcessionRequested(){
        ArrayList<Concession> concessions1 = new ArrayList<>();
        ArrayList<Concession> concessions2 = new ArrayList<>();
        Concession concession1 = Mockito.mock(Concession.class);
        concessions1.add(concession1);
        Concession concession2 = Mockito.mock(Concession.class);
        concessions2.add(concession2);
        Concession concession3 = Mockito.mock(Concession.class);
        concessions2.add(concession3);
        enrollmentDatabase.addConcessions("usr123",concessions1);
        enrollmentDatabase.addConcessions("usr123",concessions2);
        ArrayList<Concession> result = enrollmentDatabase.getConcessions("usr123");
        assertNotEquals(null,result);
        assertEquals(3, result.size());
        assertTrue(result.contains(concession1) & result.contains(concession2) & result.contains(concession3));
    }
}
