import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class TestCourseCatalog {

    private CourseCatalog database;

    @Before
    public void setUp(){
        database = new CourseCatalog();
    }

    @Test
    public void Should_ReturnListOfCourses_When_CoursesRequested(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        database.add(mockedCourse);
        database.add(mockedCourse2);
        assertEquals(2, database.getAllCourses().size());
    }

    @Test
    public void Should_ReturnSingleCourse_When_WholeCourseNameSearched(){
        Course mockedCourse = Mockito.mock(Course.class);
        Course mockedCourse1 = Mockito.mock(Course.class);
        Course mockedCourse2 = Mockito.mock(Course.class);
        Mockito.when(mockedCourse.getCourseName()).thenReturn("SOFTENG 701");
        Mockito.when(mockedCourse1.getCourseName()).thenReturn("SOFTENG 754");
        Mockito.when(mockedCourse2.getCourseName()).thenReturn("SOFTENG 750");
        database.add(mockedCourse);
        database.add(mockedCourse1);
        database.add(mockedCourse2);
        ArrayList<Course> results = database.search("SOFTENG 701");
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
        database.add(mockedCourse);
        database.add(mockedCourse1);
        database.add(mockedCourse2);
        database.add(mockedCourse3);
        ArrayList<Course> results = database.search("SOFTENG");
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
        database.add(mockedCourse);
        database.add(mockedCourse1);
        database.add(mockedCourse2);
        ArrayList<Course> results = database.search("COMPSYS 741");
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
        database.add(mockedCourse);
        database.add(mockedCourse1);
        database.add(mockedCourse2);
        ArrayList<Course> results = database.search("");
        assertEquals(0, results.size());
    }
}
