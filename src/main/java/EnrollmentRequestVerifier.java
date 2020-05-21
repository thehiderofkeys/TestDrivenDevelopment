import java.util.ArrayList;

public class EnrollmentRequestVerifier {
    public EnrollmentRequestVerifier(EnrollmentDateVerifier edv, ClashVerifier cv, PrerequisitesVerifier pv) {
    }

    public EnrollmentRejection verify(String user123, ArrayList<Course> enrollList, EnrollmentDatabase mockDB) {
        return null;
    }

    public class EnrollmentRejection{

        public ArrayList<Course> getCourses() {
            return null;
        }
    }
}
