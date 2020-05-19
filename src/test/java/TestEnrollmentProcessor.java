import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestEnrollmentProcessor {
    private EnrollmentProcessor enrollmentProcessor;
    private Course mockCourse;
    private EnrollmentDatabase mockDB;

    @Before
    public void setUp(){
        mockDB = Mockito.mock(EnrollmentDatabase.class);
        mockCourse = Mockito.mock(Course.class);
        enrollmentProcessor = new EnrollmentProcessor(mockDB);
    }

    @Test
    public void Should_QueueEnrollmentRequests_When_EnrollmentRequested(){
        ArrayList<Course> courses = new ArrayList<>();
        EnrollmentRequest request1 = new EnrollmentRequest("usr123",courses);
        EnrollmentRequest request2 = new EnrollmentRequest("usr123",courses);
        enrollmentProcessor.requestEnrollment(request1);
        enrollmentProcessor.requestEnrollment(request2);
        EnrollmentRequest result;
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, request1);
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, request2);
        result = enrollmentProcessor.getNextRequest();
        assertNull(result);
    }

    @Test
    public void Should_Enroll_When_EnrollmentRequested(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(mockCourse);
        EnrollmentRequest request = new EnrollmentRequest("usr123",courses);
        enrollmentProcessor.requestEnrollment(request);
        Mockito.when(mockCourse.reserveSeat()).thenReturn(true);
        EnrollmentProcessor.RequestResult result = enrollmentProcessor.processNextRequest();
        assertEquals(EnrollmentProcessor.RequestResult.ENROLLED, result);
        Mockito.verify(mockDB).addEnrollment("usr123",courses);
    }
}
