import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class DateVerifierEnrollVerifierIT {

    private Course desiredCourse;
    private EnrollmentRequestVerifier erv;
    private EnrollmentDateVerifier edv;
    private EnrollmentDatabase database;


    @Before
    public void setUp() {
        desiredCourse =  Mockito.mock(Course.class);
        Mockito.when(desiredCourse.getOpeningDate()).thenReturn(LocalDateTime.parse("2014-03-28T16:00:49.455"));
        Mockito.when(desiredCourse.getClosingDate()).thenReturn(LocalDateTime.parse("2014-05-28T16:00:49.455"));
        ClashVerifier cv = Mockito.mock(ClashVerifier.class);
        database = Mockito.mock(EnrollmentDatabase.class);
        Mockito.when(cv.checkClash(Mockito.any())).thenReturn(new ArrayList<>());
        PrerequisitesVerifier pv = Mockito.mock(PrerequisitesVerifier.class);
        Mockito.when(pv.checkPrerequisites(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(new ArrayList<>());
        edv = new EnrollmentDateVerifier();
        erv = new EnrollmentRequestVerifier(edv,cv,pv);
    }

    @Test
    public void TestBeforeEnrollment(){
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(desiredCourse);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.parse("2014-03-28T14:00:49.455"));
        assertEquals(enrollList, result.getCourses());
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.CLOSED), result.getReason(desiredCourse));
    }

    @Test
    public void TestDuringEnrollment(){
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(desiredCourse);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.parse("2014-04-28T18:00:49.455"));
        assertEquals(new ArrayList<Course>(), result.getCourses());
        assertEquals(null, result.getReason(desiredCourse));
    }

    @Test
    public void TestAfterEnrollment(){
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(desiredCourse);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.parse("2014-05-28T18:00:49.455"));
        assertEquals(enrollList, result.getCourses());
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.CLOSED), result.getReason(desiredCourse));
    }
}
