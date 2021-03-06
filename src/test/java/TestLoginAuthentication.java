import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class TestLoginAuthentication {

    private LoginAuthenticator loginAuthenticator;

    @Before
    public void setUp(){
        loginAuthenticator = new LoginAuthenticator();

        loginAuthenticator.addUserCredentials("snea123", "password1");
        loginAuthenticator.addUserCredentials("rlim456", "password2");
    }

    @Test
    public void should_ReturnTrue_When_CredentialsCorrect(){
        boolean isLoggedIn = loginAuthenticator.login("snea123", "password1");
        assertTrue(isLoggedIn);
    }

    @Test
    public void should_ReturnFalse_When_CredentialsIncorrect(){
        boolean isLoggedIn = loginAuthenticator.login("rlim456", "password4");
        assertFalse(isLoggedIn);
    }
}
