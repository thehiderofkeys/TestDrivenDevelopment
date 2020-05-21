import java.time.LocalDateTime;
import java.util.*;

public class EnrollmentRequestVerifier {
    private EnrollmentDateVerifier edv;
    private ClashVerifier cv;
    private PrerequisitesVerifier pv;

    public EnrollmentRequestVerifier(EnrollmentDateVerifier edv, ClashVerifier cv, PrerequisitesVerifier pv) {
        this.edv = edv;
        this.pv = pv;
        this.cv = cv;
    }

    public EnrollmentRejection verify(String username, ArrayList<Course> enrollList, EnrollmentDatabase database) {
        ArrayList<Course> closedCourses = new ArrayList<>();
        for (Course course: enrollList){
            if(!edv.isEnrollmentOpen(course, LocalDateTime.now())) {
                closedCourses.add(course);
            }
        }
        ArrayList<Course> noPrereq = pv.checkPrerequisites(enrollList,username,database);
        ArrayList<Course> clashes = cv.checkClash(enrollList);
        return new EnrollmentRejection(closedCourses,noPrereq,clashes);
    }

    public class EnrollmentRejection{
        private HashMap<Course,EnumSet<Reason>> declined;
        private EnrollmentRejection(ArrayList<Course> closedCourses, ArrayList<Course> noPrereq, ArrayList<Course> clashes){
            declined = new HashMap<>();
            for (Course course: closedCourses){
                declined.put(course,EnumSet.of(Reason.CLOSED));
            }
            for (Course course: noPrereq){
                if (!declined.containsKey(course))
                    declined.put(course,EnumSet.of(Reason.PREREQ));
                else
                    declined.get(course).add(Reason.PREREQ);
            }
            for (Course course: clashes){
                if (!declined.containsKey(course))
                    declined.put(course,EnumSet.of(Reason.CLASH));
                else
                    declined.get(course).add(Reason.CLASH);
            }
        }
        public ArrayList<Course> getCourses() {
            return new ArrayList<>(declined.keySet());
        }

        public EnumSet<Reason> getReason(Course course) {
            return declined.get(course);
        }
    }
    public enum Reason{CLOSED,PREREQ,CLASH}
}
