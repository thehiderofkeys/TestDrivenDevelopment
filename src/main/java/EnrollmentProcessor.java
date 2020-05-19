import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EnrollmentProcessor {
    private LinkedList<EnrollmentRequest> queue;
    public EnrollmentProcessor() {
        queue = new LinkedList<>();
    }
    public void requestEnrollment(EnrollmentRequest request2) {
        queue.addLast(request2);
    }

    public EnrollmentRequest getNextRequest() {
        try {
            return queue.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
