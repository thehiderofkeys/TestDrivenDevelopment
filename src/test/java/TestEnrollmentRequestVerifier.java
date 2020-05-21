import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.AdditionalMatchers;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

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
}
