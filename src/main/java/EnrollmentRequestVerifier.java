import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

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
        Set<Course> deniedList = new HashSet<>();
        ArrayList<Course> closedCourses = new ArrayList<>();
        for (Course course: enrollList){
            if(!edv.isEnrollmentOpen(course, LocalDateTime.now())) {
                closedCourses.add(course);
            }
        }
        ArrayList<Course> noPrereq = pv.checkPrerequisites(enrollList,username,database);
        ArrayList<Course> clashes = cv.checkClash(enrollList);
        deniedList.addAll(closedCourses);
        deniedList.addAll(noPrereq);
        deniedList.addAll(clashes);
        return new EnrollmentRejection(new ArrayList<>(deniedList));
    }

    public class EnrollmentRejection{
        private ArrayList<Course> enrollList;
        private EnrollmentRejection(ArrayList<Course> enrollList){
            this.enrollList = enrollList;
        }
        public ArrayList<Course> getCourses() {
            return enrollList;
        }

        public EnumSet<Reason> getReason(Course course1) {
            return null;
        }
    }
    public enum Reason{CLOSED,PREREQ,CLASH}
}
