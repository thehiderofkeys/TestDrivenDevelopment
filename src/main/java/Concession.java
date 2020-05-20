public class Concession {
    private Course course;
    private String username;
    public Concession(String username, Course course) {
        this.username = username;
        this.course = course;
    }

    public String getUsername() {
        return username;
    }

    public Course getCourse() {
        return course;
    }
    @Override @CoverageIgnore
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Concession))
            return false;
        Concession c = (Concession)o;
        return username.equals(c.getUsername()) && course.equals(c.course);
    }
}
