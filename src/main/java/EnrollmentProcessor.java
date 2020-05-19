import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EnrollmentProcessor {
    private LinkedList<RequestObject> queue;
    private EnrollmentDatabase database;
    public EnrollmentProcessor(EnrollmentDatabase database) {
        this.database = database;
        queue = new LinkedList<>();
    }
    public void requestEnrollment(EnrollmentRequest request) {
        queue.addLast(request);
    }

    public void requestConcession(ConcessionRequest request) {
        queue.addLast(request);
    }

    public RequestObject getNextRequest() {
        try {
            return queue.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public RequestResult processNextRequest() {
        RequestObject request = getNextRequest();
        if(request instanceof EnrollmentRequest) {
            EnrollmentRequest enrollmentRequest = (EnrollmentRequest)request;
            database.addEnrollment(enrollmentRequest.getUsername(), enrollmentRequest.getCourses());
            return RequestResult.ENROLLED;
        }
        if(request instanceof ConcessionRequest) {
            ConcessionRequest enrollmentRequest = (ConcessionRequest)request;
            database.addConcessions(enrollmentRequest.getUsername(), enrollmentRequest.getConcession());
            return RequestResult.CONCESSION_APPLIED;
        }
        return null;
    }

    public void approveConcession(Concession concession) {
        String username = concession.getUsername();
        Course course = concession.getCourse();
        ArrayList<Course> enrollment = new ArrayList<>();
        enrollment.add(course);
        database.addEnrollment(username,enrollment);
    }

    public void declineConcession(Concession concession) {
    }

    public enum RequestResult {CONCESSION_APPLIED, ENROLLED}
}
