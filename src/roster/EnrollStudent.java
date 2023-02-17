package roster;

public class EnrollStudent  {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent(String lname, String fname, Date dob, int creditsEnrolled) {
        profile = new Profile(lname, fname, dob);
        this.creditsEnrolled = creditsEnrolled;
    }

    @Override
    public String toString() {
        return profile.toString() + " enrolled in " + creditsEnrolled + " credits";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EnrollStudent) {
            EnrollStudent student = (EnrollStudent)obj;
            return profile.equals(student.profile);
        }
        return false;
    }

    public void setCredits(int credits) {
        creditsEnrolled = credits;
    }

    public int getCredits() {
        return creditsEnrolled;
    }

    public Profile getProfile() {
        return profile;
    }
}
