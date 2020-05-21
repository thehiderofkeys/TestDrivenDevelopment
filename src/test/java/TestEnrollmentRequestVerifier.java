import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.AdditionalMatchers;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Matchers.eq;

public class TestEnrollmentRequestVerifier {
    private EnrollmentRequestVerifier requestVerifier;
    private EnrollmentDateVerifier edv;
    private ClashVerifier cv;
    private PrerequisitesVerifier pv;
    private EnrollmentDatabase mockDB;

    @Before
    public void setUp(){
        mockDB = Mockito.mock(EnrollmentDatabase.class);
        edv = Mockito.mock(EnrollmentDateVerifier.class);
        cv = Mockito.mock(ClashVerifier.class);
        pv = Mockito.mock(PrerequisitesVerifier.class);
        requestVerifier = new EnrollmentRequestVerifier(edv,cv,pv);
    }
    @Test
    public void Should_ReturnDeniedList_When_EnrollmentDenied(){
        Course mockCourse = Mockito.mock(Course.class);
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(mockCourse);
        Mockito.when(edv.isEnrollmentOpen(Mockito.any(),Mockito.any())).thenReturn(false);
        Mockito.when(pv.checkPrerequisites(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(enrollList);
        Mockito.when(cv.checkClash(Mockito.any())).thenReturn(enrollList);
        EnrollmentRequestVerifier.EnrollmentRejection result = requestVerifier.verify("user123",enrollList,mockDB);
        assertEquals(enrollList,result.getCourses());
    }
    @Test
    public void Should_ReturnEmptyList_When_EnrollmentSuccessful(){
        Course mockCourse = Mockito.mock(Course.class);
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(mockCourse);
        Mockito.when(edv.isEnrollmentOpen(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(pv.checkPrerequisites(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(cv.checkClash(Mockito.any())).thenReturn(new ArrayList<>());
        EnrollmentRequestVerifier.EnrollmentRejection result = requestVerifier.verify("user123",enrollList,mockDB);
        assertEquals(new ArrayList<>(),result.getCourses());
    }
    @Test
    public void Should_ReturnReasonForEach_When_EnrollmentDenied(){
        ArrayList<Course> enrollList = new ArrayList<>();
        Course course1 = Mockito.mock(Course.class);
        enrollList.add(course1);
        Course course2 = Mockito.mock(Course.class);
        enrollList.add(course2);
        Course course3 = Mockito.mock(Course.class);
        enrollList.add(course3);
        Mockito.when(edv.isEnrollmentOpen(eq(course1),Mockito.any())).thenReturn(true);
        Mockito.when(edv.isEnrollmentOpen(not(eq(course1)),Mockito.anyObject())).thenReturn(false);
        ArrayList<Course> noPrereq = new ArrayList<>();
        noPrereq.add(course2);
        Mockito.when(pv.checkPrerequisites(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(noPrereq);
        ArrayList<Course> clashing = new ArrayList<>();
        noPrereq.add(course2);
        noPrereq.add(course2);
        Mockito.when(cv.checkClash(Mockito.any())).thenReturn(clashing);
        EnrollmentRequestVerifier.EnrollmentRejection result = requestVerifier.verify("user123",enrollList,mockDB);
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.CLOSED),result.getReason(course1));
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.PREREQ,EnrollmentRequestVerifier.Reason.CLASH),
                result.getReason(course2));
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.CLASH),result.getReason(course3));
    }
}
