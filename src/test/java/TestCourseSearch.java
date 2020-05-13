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
        assertEquals(courseCatalog.getAllCourses().size(), 2);
    }

    @Test
    public void Should_ReturnSingleCourse_When_WholeCourseNameSearched(){
        Course mockedCourse = Mockito.mock(Course.class);
        Mockito.when(mockedCourse.getCourseName()).thenReturn("SOFTENG 701");
        courseCatalog.add(mockedCourse);
        ArrayList<Course> results = courseCatalog.search("SOFTENG 701");
        assertEquals(results.size(), 1);
        assertTrue(results.get(0).getCourseName(0).equals("SOFTENG 701"));
    }
}
