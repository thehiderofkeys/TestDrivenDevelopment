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

    public EnrollmentRequest getNextRequest() {
        try {
            return queue.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public RequestResult processNextRequest() {
        EnrollmentRequest request = getNextRequest();
        database.addEnrollment(request.getUsername(),request.getCourses());
        return RequestResult.ENROLLED;
    }

    public enum RequestResult {ENROLLED}
}
