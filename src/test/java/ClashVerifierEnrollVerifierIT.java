import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Category(IntegrationTest.class)
public class ClashVerifierEnrollVerifierIT {

    private Course course1;
    private Course course2;
    private Course course3;
    private EnrollmentRequestVerifier erv;
    private EnrollmentDatabase database;


    @Before
    public void setUp() {
        course1 =  Mockito.mock(Course.class);
        course2 =  Mockito.mock(Course.class);
        course3 =  Mockito.mock(Course.class);

        TimetableEvent event1 = Mockito.mock(TimetableEvent.class);
        Mockito.when(event1.getStartTime()).thenReturn(LocalDateTime.parse("2014-03-28T14:00:00.000"));
        Mockito.when(event1.getDuration()).thenReturn(1);

        TimetableEvent event2 = Mockito.mock(TimetableEvent.class);
        Mockito.when(event2.getStartTime()).thenReturn(LocalDateTime.parse("2014-03-28T15:00:00.000"));
        Mockito.when(event2.getDuration()).thenReturn(1);

        ArrayList<TimetableEvent> eventList1 = new ArrayList<>();
        eventList1.add(event1);

        ArrayList<TimetableEvent> eventList2 = new ArrayList<>();
        eventList2.add(event2);

        Mockito.when(course1.getCourseSchedule()).thenReturn(eventList1);
        Mockito.when(course2.getCourseSchedule()).thenReturn(eventList2);
        Mockito.when(course3.getCourseSchedule()).thenReturn(eventList1);

        EnrollmentDateVerifier edv = Mockito.mock(EnrollmentDateVerifier.class);
        PrerequisitesVerifier pv = Mockito.mock(PrerequisitesVerifier.class);
        database = Mockito.mock(EnrollmentDatabase.class);
        Mockito.when(edv.isEnrollmentOpen(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(pv.checkPrerequisites(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(new ArrayList<>());
        ClashVerifier cv = new ClashVerifier();
        erv = new EnrollmentRequestVerifier(edv,cv,pv);
    }

    @Test
    public void TestClashingEnrollment(){
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(course1);
        enrollList.add(course3);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.now());
        assertEquals(enrollList, result.getCourses());
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.CLASH), result.getReason(course1));
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.CLASH), result.getReason(course3));
    }

    @Test
    public void TestCompatibleEnrollment(){
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(course1);
        enrollList.add(course3);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.now());
        assertEquals(enrollList, result.getCourses());
        assertNull(result.getReason(course1));
        assertNull(result.getReason(course3));
    }
}
