import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class LoginAndEnrolIT {

    private LoginAuthenticator loginAuthenticator;
    private EnrollmentDatabase enrollmentDatabase;
    private String userEnteredUserName;
    private String userEnteredPassword;

    @Before
    public void setUp(){
        loginAuthenticator = new LoginAuthenticator();
        enrollmentDatabase = new EnrollmentDatabase();

        userEnteredPassword = "Password123";
        userEnteredUserName = "PabloSushibar";

        loginAuthenticator.addUserCredentials("PabloSushibar", "Password123");
    }

    @Test
    public void TestValidCredentialsAndEnrol(){
        ArrayList<Course> result = new ArrayList<>();
        Course course1 = Mockito.mock(Course.class);
        Course course2 = Mockito.mock(Course.class);
        Course course3 = Mockito.mock(Course.class);

        boolean isLoggedIn = loginAuthenticator.login(userEnteredUserName, userEnteredPassword);
        if (isLoggedIn){
            ArrayList<Course> desiredCourses = new ArrayList<>();
            desiredCourses.add(course1);
            desiredCourses.add(course2);
            desiredCourses.add(course3);
            enrollmentDatabase.addEnrollment("usr123",desiredCourses);
            result = enrollmentDatabase.getEnrolledCourses("usr123");
        }

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(course1) & result.contains(course2) & result.contains(course3));
    }
}
