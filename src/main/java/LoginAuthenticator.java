import java.util.HashMap;

public class LoginAuthenticator {
    private HashMap<String, String> passwordDatabase = new HashMap<>();

    public void addUserCredentials(String userName, String password){
        passwordDatabase.put(userName, password);
    }

    public boolean login(String userName, String password){
        return true;
    }
}
