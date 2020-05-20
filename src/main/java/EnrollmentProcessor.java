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
            RequestResult result = RequestResult.ENROLLED;
            EnrollmentRequest enrollmentRequest = (EnrollmentRequest)request;
            ArrayList<Course> availCourses = new ArrayList<>();
            for (Course course : enrollmentRequest.getCourses()){
                if(course.reserveSeat()){
                    availCourses.add(course);
                }
                else{
                    ArrayList<Course> courseList = new ArrayList<>();
                    courseList.add(course);
                    EnrollmentRequest waitListedRequest = new EnrollmentRequest(
                            enrollmentRequest.getUsername(),courseList);
                    course.addToWaitList(waitListedRequest);
                    result = RequestResult.WAITLISTED;
                }
            }
            database.addEnrollment(enrollmentRequest.getUsername(), availCourses);
            return result;
        }
        if(request instanceof ConcessionRequest) {
            RequestResult result = RequestResult.CONCESSION_APPLIED;
            ConcessionRequest concessionRequest = (ConcessionRequest)request;
            ArrayList<Concession> availConcessions = new ArrayList<>();
            for (Concession concession : concessionRequest.getConcession()){
                if(concession.getCourse().reserveSeat()){
                    availConcessions.add(concession);
                }
                else{
                    ArrayList<Concession> concessionList = new ArrayList<>();
                    concessionList.add(concession);
                    ConcessionRequest waitListedRequest = new ConcessionRequest(
                            concessionRequest.getUsername(),concessionList);
                    concession.getCourse().addToWaitList(waitListedRequest);
                    result = RequestResult.WAITLISTED;
                }
            }
            database.addConcessions(concessionRequest.getUsername(), availConcessions);
            return result;
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
        String username = concession.getUsername();
        Course course = concession.getCourse();
        RequestObject request = course.popWaitList();
        if (request == null) {
            course.releaseSeat();
        }
        else {
            if(request instanceof EnrollmentRequest) {
                EnrollmentRequest enrollmentRequest = (EnrollmentRequest)request;
                database.addEnrollment(enrollmentRequest.getUsername(), enrollmentRequest.getCourses());
            }
            else {
                ConcessionRequest concessionRequest = (ConcessionRequest)request;
                database.addConcessions(concessionRequest.getUsername(), concessionRequest.getConcession());
            }
        }
    }

    public enum RequestResult {CONCESSION_APPLIED, WAITLISTED, ENROLLED}
}
