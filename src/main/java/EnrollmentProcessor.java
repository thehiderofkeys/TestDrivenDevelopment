import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EnrollmentProcessor {
    private LinkedList<EnrollmentRequest> queue;
    private EnrollmentDatabase database;
    public EnrollmentProcessor(EnrollmentDatabase database) {
        this.database = database;
        queue = new LinkedList<>();
    }
    public void requestEnrollment(EnrollmentRequest request) {
        queue.addLast(request);
    }

    public void requestConcession(ConcessionRequest request1) {
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
        return null;
    }

    public enum RequestResult {ENROLLED}
}
