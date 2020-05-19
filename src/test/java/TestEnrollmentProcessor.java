import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class TestEnrollmentProcessor {
    private EnrollmentProcessor enrollmentProcessor;
    private Course mockCourse;

    @Before
    public void setUp(){
        mockCourse = Mockito.mock(Course.class);
        enrollmentProcessor = new EnrollmentProcessor();
    }
    @Test
    public void Should_QueueEnrollmentRequests_When_EnrollmentRequested(){
        EnrollmentRequest request1 = new EnrollmentRequest("usr123",mockCourse);
        EnrollmentRequest request2 = new EnrollmentRequest("usr123",mockCourse);
        enrollmentProcessor.requestEnrollment(request1);
        enrollmentProcessor.requestEnrollment(request2);
        EnrollmentRequest result;
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, request1);
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, request2);
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, null);
    }

}
