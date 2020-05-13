import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

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
}
