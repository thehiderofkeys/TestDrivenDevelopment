import java.util.ArrayList;

public class EnrollmentRequestVerifier {
    public EnrollmentRequestVerifier(EnrollmentDateVerifier edv, ClashVerifier cv, PrerequisitesVerifier pv) {
    }

    public EnrollmentRejection verify(String username, ArrayList<Course> enrollList, EnrollmentDatabase database) {
        return new EnrollmentRejection(enrollList);
    }

    public class EnrollmentRejection{
        private ArrayList<Course> enrollList;
        private EnrollmentRejection(ArrayList<Course> enrollList){
            this.enrollList = enrollList;
        }
        public ArrayList<Course> getCourses() {
            return enrollList;
        }
    }
}
