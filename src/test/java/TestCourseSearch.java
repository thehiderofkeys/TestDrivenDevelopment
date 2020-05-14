import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCourseSearch {

    private CourseCatalog courseCatalog;

    @Before
    public void setUp(){
        courseCatalog = new CourseCatalog();
    }

    @Test
    public void Should_ReturnListOfCourses_When_CoursesRequested(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        courseCatalog.add(mockedCourse);
        courseCatalog.add(mockedCourse2);
        assertEquals(2, courseCatalog.getAllCourses().size());
    }

    @Test
    public void Should_ReturnSingleCourse_When_WholeCourseNameSearched(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Mockito.when(mockedCourse.getCourseName()).thenReturn("SOFTENG 701");
        Mockito.when(mockedCourse1.getCourseName()).thenReturn("SOFTENG 754");
        Mockito.when(mockedCourse2.getCourseName()).thenReturn("SOFTENG 750");
        courseCatalog.add(mockedCourse);
        courseCatalog.add(mockedCourse1);
        courseCatalog.add(mockedCourse2);
        ArrayList<Course> results = courseCatalog.search("SOFTENG 701");
        assertEquals(1, results.size());
        assertTrue(results.contains(mockedCourse));
    }

    @Test
    public void Should_ReturnMultipleCourses_When_PartialCourseNameSearched(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Course mockedCourse3 = Mockito.mock(Course.class);
        Mockito.when(mockedCourse.getCourseName()).thenReturn("SOFTENG 701");
        Mockito.when(mockedCourse1.getCourseName()).thenReturn("SOFTENG 754");
        Mockito.when(mockedCourse2.getCourseName()).thenReturn("SOFTENG 750");
        Mockito.when(mockedCourse3.getCourseName()).thenReturn("COMPSYS 201");
        courseCatalog.add(mockedCourse);
        courseCatalog.add(mockedCourse1);
        courseCatalog.add(mockedCourse2);
        courseCatalog.add(mockedCourse3);
        ArrayList<Course> results = courseCatalog.search("SOFTENG");
        assertEquals(3, results.size());
        assertTrue(results.contains(mockedCourse));
        assertTrue(results.contains(mockedCourse1));
        assertTrue(results.contains(mockedCourse2));
    }

    @Test
    public void Should_ReturnEmpty_When_NonexistingCourseName(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Mockito.when(mockedCourse.getCourseName()).thenReturn("SOFTENG 701");
        Mockito.when(mockedCourse1.getCourseName()).thenReturn("SOFTENG 754");
        Mockito.when(mockedCourse2.getCourseName()).thenReturn("SOFTENG 750");
        courseCatalog.add(mockedCourse);
        courseCatalog.add(mockedCourse1);
        courseCatalog.add(mockedCourse2);
        ArrayList<Course> results = courseCatalog.search("COMPSYS 741");
        assertEquals(0, results.size());
    }

    @Test
    public void Should_ReturnEmpty_When_EmptySearch(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Mockito.when(mockedCourse.getCourseName()).thenReturn("SOFTENG 701");
        Mockito.when(mockedCourse1.getCourseName()).thenReturn("SOFTENG 754");
        Mockito.when(mockedCourse2.getCourseName()).thenReturn("SOFTENG 750");
        courseCatalog.add(mockedCourse);
        courseCatalog.add(mockedCourse1);
        courseCatalog.add(mockedCourse2);
        ArrayList<Course> results = courseCatalog.search("");
        assertEquals(0, results.size());
    }
}
