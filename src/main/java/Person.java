import java.util.ArrayList;

public class Person {

    private String name;
    private ArrayList<CourseRole> roles;

    public Person(String name, ArrayList<CourseRole> roles){
        this.name = name;
        this.roles = roles;
    }

     enum CourseRole {
        Lecturer,
        CourseCoordinator,
        TA
    }
}
