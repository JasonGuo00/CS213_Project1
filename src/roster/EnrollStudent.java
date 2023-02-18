package roster;

/**
 * Object that holds a Profile object and an integer representing the number of credits a student intends to enroll with.
 * Contains getters and setters, and a toString and equals override.
 * @author Jason Guo, Russel Rivera
 */
public class EnrollStudent  {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * Constructor for EnrollStudent.
     * @param lname Last name of the student.
     * @param fname First name of the student.
     * @param dob D.O.B. of the student.
     * @param creditsEnrolled Number of credits intending to enroll with.
     */
    public EnrollStudent(String lname, String fname, Date dob, int creditsEnrolled) {
        profile = new Profile(lname, fname, dob);
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Set the number of credits the student is enrolling with.
     * @param credits Number of credits enrolling with.
     */
    public void setCredits(int credits) {
        creditsEnrolled = credits;
    }

    /**
     * Get the number of credits the student is enrolling with.
     * @return creditsEnrolled: Number of credits enrolling with.
     */
    public int getCredits() {
        return creditsEnrolled;
    }

    /**
     * Get the Profile of the EnrollStudent.
     * @return profile: The Profile of the EnrollStudent.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Override of the toString() method.  Prints out the Profile of the student and the number of credits they're
     * enrolling with.
     * @return String containing the information in the description.
     */
    @Override
    public String toString() {
        return profile.toString() + " enrolled " + creditsEnrolled + " credits";
    }

    /**
     * Override of the equals() method.  Checks if two EnrollStudent objects are equal by comparing their Profile.
     * @param obj Object to be compared with.
     * @return true or false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EnrollStudent) {
            EnrollStudent student = (EnrollStudent)obj;
            return profile.equals(student.profile);
        }
        return false;
    }
}
