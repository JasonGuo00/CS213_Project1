package roster;

/**
 * NonResident is a subclass of Student, used to classify students who don't live in NJ.
 * @author Jason Guo, Russel Rivera
 */
public class NonResident extends Student {

    /**
     * Constructor for NonResident object.
     * @param profile Profile of the student.
     * @param major Student's declared major.
     * @param creditCompleted Number of credits that the student has completed.
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    /**
     * Calculate the part-time tuition the student must pay based on the number of credits they've enrolled in.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    private double partTime(int creditsEnrolled) {
        double total = Constants.PART_UNI_FEES;
        total += (Constants.NRES_CPH * creditsEnrolled);
        return total;
    }
    /**
     * Calculate the full-time tuition the student must pay based on the number of credits they've enrolled in.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    private double fullTime(int creditsEnrolled) {
        double total = Constants.FULL_UNI_FEES;
        total += Constants.NRES_FULL_TUITION;
        if(creditsEnrolled > Constants.STD_CREDITS) {
            total += ((creditsEnrolled - Constants.STD_CREDITS) * Constants.NRES_CPH);
        }
        return total;
    }

    /**
     * Classify student as either full-time or part-time to retrieve the correct tuition due.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled >= Constants.FULL_TIME_CREDITS) {
            return fullTime(creditsEnrolled);
        }
        return partTime(creditsEnrolled);
    }

    /**
     * Determines if the student is a resident, which will always be true for students of the Resident class.
     * @return true
     */
    @Override
    public boolean isResident() {
        return false;
    }

    /**
     * Overrides the toString method.
     * @return Returns the name, d.o.b, major code, major, school, credits completed, and seniority of the student
     */
    @Override
    public String toString() {
        return (super.toString() + "(non-resident)");
    }
}
