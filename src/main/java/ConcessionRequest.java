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
    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof ConcessionRequest))
            return false;
        ConcessionRequest cr = (ConcessionRequest)o;
        return usernames.equals(cr.getUsername()) && concessions.equals(cr.concessions);
    }
}
