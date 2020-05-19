import java.util.ArrayList;

public class ConcessionRequest implements RequestObject{
    private final String usernames;
    private final ArrayList<Concession> concessions;

    public ConcessionRequest(String usernames, ArrayList<Concession> concessions) {
        this.usernames = usernames;
        this.concessions = concessions;
    }

    public String getUsername() {
        return usernames;
    }

    public ArrayList<Concession> getConcession() {
        return concessions;
    }
}
