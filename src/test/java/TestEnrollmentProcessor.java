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
        RequestObject result;
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, request1);
        result = enrollmentProcessor.getNextRequest();
        assertEquals(result, request2);
        result = enrollmentProcessor.getNextRequest();
        assertNull(result);
    }

    @Test
    public void Should_QueueConcessionRequests_When_ConcessionRequested(){
        ArrayList<Concession> concessions = new ArrayList<>();
        ConcessionRequest request1 = new ConcessionRequest("usr123",concessions);
        ConcessionRequest request2 = new ConcessionRequest("usr123",concessions);
        enrollmentProcessor.requestConcession(request1);
        enrollmentProcessor.requestConcession(request2);
        RequestObject result;
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

    @Test
    public void Should_ApplyConcession_When_ConcessionRequested(){
        ArrayList<Concession> concessions = new ArrayList<>();
        ConcessionRequest request = new ConcessionRequest("usr123",concessions);
        enrollmentProcessor.requestConcession(request);
        Mockito.when(mockCourse.reserveSeat()).thenReturn(true);
        EnrollmentProcessor.RequestResult result = enrollmentProcessor.processNextRequest();
        assertEquals(EnrollmentProcessor.RequestResult.CONCESSION_APPLIED, result);
        Mockito.verify(mockDB).addConcessions("usr123",concessions);
    }

    @Test
    public void Should_Enroll_When_ConcessionApproved(){
        Concession concession = Mockito.mock(Concession.class);
        Mockito.when(concession.getUsername()).thenReturn("usr123");
        Mockito.when(concession.getCourse()).thenReturn(mockCourse);
        enrollmentProcessor.approveConcession(concession);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(mockCourse);
        Mockito.verify(mockDB).addEnrollment("usr123",courses);
    }

    @Test
    public void Should_ReleaseSeat_When_ConcessionDeclined() {
        Concession concession = Mockito.mock(Concession.class);
        Mockito.when(concession.getUsername()).thenReturn("usr123");
        Mockito.when(concession.getCourse()).thenReturn(mockCourse);
        enrollmentProcessor.declineConcession(concession);
        Mockito.verify(mockCourse).releaseSeat();
    }

    @Test
    public void Should_AddEnrollmentToWaitList_When_CourseIsFull(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(mockCourse);
        EnrollmentRequest request = new EnrollmentRequest("usr123",courses);;
        enrollmentProcessor.requestEnrollment(request);
        Mockito.when(mockCourse.reserveSeat()).thenReturn(false);
        EnrollmentProcessor.RequestResult result = enrollmentProcessor.processNextRequest();
        assertEquals(EnrollmentProcessor.RequestResult.WAITLISTED, result);
        Mockito.verify(mockCourse).addToWaitList(request);
    }
    @Test
    public void Should_AddConcessionToWaitList_When_CourseIsFull(){
        ArrayList<Concession> concessions = new ArrayList<>();
        concessions.add(new Concession("usr123", mockCourse));
        ConcessionRequest request = new ConcessionRequest("usr123",concessions);
        enrollmentProcessor.requestConcession(request);
        Mockito.when(mockCourse.reserveSeat()).thenReturn(false);
        EnrollmentProcessor.RequestResult result = enrollmentProcessor.processNextRequest();
        assertEquals(EnrollmentProcessor.RequestResult.WAITLISTED, result);
        Mockito.verify(mockCourse).addToWaitList(request);
    }

    @Test
    public void Should_ProcessEnrollmentInWaitList_When_ConcessionDeclinedAndWaitListIsNotEmpty() {
        Concession concession = Mockito.mock(Concession.class);
        Mockito.when(concession.getUsername()).thenReturn("usr123");
        Mockito.when(concession.getCourse()).thenReturn(mockCourse);
        ArrayList<Course> courses = new ArrayList<>();
        EnrollmentRequest waitListedEnrollment = new EnrollmentRequest("usr321",courses);
        Mockito.when(mockCourse.popWaitList()).thenReturn(waitListedEnrollment);
        enrollmentProcessor.declineConcession(concession);
        Mockito.verify(mockCourse, Mockito.never()).releaseSeat();
        Mockito.verify(mockDB).addEnrollment("usr321",courses);
    }
}
