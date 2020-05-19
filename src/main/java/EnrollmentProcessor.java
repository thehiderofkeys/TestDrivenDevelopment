import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EnrollmentProcessor {
    private LinkedList<EnrollmentRequest> queue;
    public EnrollmentProcessor() {
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
}
