import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TestEnrollmentDateVerifier{
    private EnrollmentDateVerifier verifier;

    @Before
    public void setUp() {
        verifier = new EnrollmentDateVerifier();
    }

    @Test
    public void Should_ReturnEnrollmentOpen_When_DuringEnrollmentDates(){
        Course desiredCourse =  Mockito.mock(Course.class);
        Mockito.when(desiredCourse.getOpeningDate()).thenReturn(LocalDateTime.parse("2014-03-28T16:00:49.455"));
        Mockito.when(desiredCourse.getClosingDate()).thenReturn(LocalDateTime.parse("2014-05-28T16:00:49.455"));
        assertTrue(verifier.isEnrollmentOpen(desiredCourse, LocalDateTime.parse("2014-04-28T16:00:49.455")));
    }

    @Test
    public void Should_ReturnEnrollmentClosed_When_BeforeEnrollmentOpens(){
        Course desiredCourse =  Mockito.mock(Course.class);
        Mockito.when(desiredCourse.getOpeningDate()).thenReturn(LocalDateTime.parse("2014-03-28T16:00:49.455"));
        Mockito.when(desiredCourse.getClosingDate()).thenReturn(LocalDateTime.parse("2014-05-28T16:00:49.455"));
        assertFalse(verifier.isEnrollmentOpen(desiredCourse, LocalDateTime.parse("2014-02-28T16:00:49.455")));
    }

    @Test
    public  void Should_ReturnEnrollmentClosed_When_AfterEnrollmentClosed(){
        Course desiredCourse =  Mockito.mock(Course.class);
        Mockito.when(desiredCourse.getOpeningDate()).thenReturn(LocalDateTime.parse("2014-03-28T16:00:49.455"));
        Mockito.when(desiredCourse.getClosingDate()).thenReturn(LocalDateTime.parse("2014-05-28T16:00:49.455"));
        assertFalse(verifier.isEnrollmentOpen(desiredCourse, LocalDateTime.parse("2014-06-28T16:00:49.455")));
    }
}
