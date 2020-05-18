import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

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
        boolean isLoggedIn = loginAuthenticator.login("username", "password");
        assertTrue(isLoggedIn);
    }
}
