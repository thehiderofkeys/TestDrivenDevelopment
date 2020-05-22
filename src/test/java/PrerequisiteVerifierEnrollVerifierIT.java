import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;


@Category(IntegrationTest.class)
public class PrerequisiteVerifierEnrollVerifierIT {

    private Course desiredCourse;
    private Course prerequisiteCourse;
    private EnrollmentRequestVerifier erv;
    private EnrollmentDatabase database;

    @Before
    public void setUp() {
        desiredCourse =  Mockito.mock(Course.class);
        ArrayList<String> prerequisites = new ArrayList<>();
        prerequisites.add("SOFTENG 701");
        Mockito.when(desiredCourse.getPrerequisites()).thenReturn(prerequisites);
        prerequisiteCourse =  Mockito.mock(Course.class);
        Mockito.when(prerequisiteCourse.getCourseName()).thenReturn("SOFTENG 701");
        EnrollmentDateVerifier edv = Mockito.mock(EnrollmentDateVerifier.class);
        ClashVerifier cv = Mockito.mock(ClashVerifier.class);
        PrerequisitesVerifier pv = new PrerequisitesVerifier();
        database = Mockito.mock(EnrollmentDatabase.class);
        Mockito.when(edv.isEnrollmentOpen(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(cv.checkClash(Mockito.any())).thenReturn(new ArrayList<>());
        erv = new EnrollmentRequestVerifier(edv,cv,pv);
    }

    @Test
    public void TestPrerequisiteNotMet(){
        Mockito.when(database.getCompletedCourses("user123")).thenReturn(new ArrayList<>());
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(desiredCourse);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.now());
        assertEquals(enrollList, result.getCourses());
        assertEquals(EnumSet.of(EnrollmentRequestVerifier.Reason.PREREQ), result.getReason(desiredCourse));
    }

    @Test
    public void TestPrerequisiteMet(){
        ArrayList<Course> prereqList = new ArrayList<>();
        prereqList.add(prerequisiteCourse);
        Mockito.when(database.getCompletedCourses("user123")).thenReturn(prereqList);
        ArrayList<Course> enrollList = new ArrayList<>();
        enrollList.add(desiredCourse);
        EnrollmentRequestVerifier.EnrollmentRejection result =
                erv.verify("user123",enrollList,database,LocalDateTime.now());
        assertEquals(new ArrayList<>(), result.getCourses());
        assertEquals(null, result.getReason(desiredCourse));
    }
}
