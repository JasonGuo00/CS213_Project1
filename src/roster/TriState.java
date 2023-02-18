package roster;

/**
 * TriState is a subclass of NonResident, used to classify students who live in NY or CT.
 * @author Jason Guo, Russel Rivera
 */
public class TriState extends NonResident{
    private String state;

    /**
     * Constructor for TriState object.
     * @param profile Profile of the student.
     * @param major Student's declared major.
     * @param creditCompleted Number of credits that the student has completed.
     * @param state The state that the student lives in.
     */
    public TriState(Profile profile, Major major, int creditCompleted, String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }

    /**
     * Returns the state that the student lives in.
     * @return A String representing the state that the student lives in.
     */
    public String getState() {
        return state;
    }

    /**
     * Determines the tuition that the TriState student owes based on credits enrolled and state of residence.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return Tuition the student owes.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled >= Constants.FULL_TIME_CREDITS) {
            return fullTime(creditsEnrolled);
        }
        return partTime(creditsEnrolled);
    }

    /**
     * Determines if the student is a resident of NJ or not.
     * @return false for TriState students.
     */
    @Override
    public boolean isResident() {
        return false;
    }

    /**
     * Calculate the part-time tuition the student must pay based on the number of credits they've enrolled in and state of residence.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    private double partTime(int creditsEnrolled) {
        double total = Constants.PART_UNI_FEES;
        total += (Constants.NRES_CPH * creditsEnrolled);
        return total;
    }
    /**
     * Calculate the full-time tuition the student must pay based on the number of credits they've enrolled in and state of residence.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    private double fullTime(int creditsEnrolled) {
        double total = Constants.FULL_UNI_FEES;
        total += Constants.NRES_FULL_TUITION;
        if (creditsEnrolled > Constants.STD_CREDITS) {
            total += ((creditsEnrolled - Constants.STD_CREDITS) * Constants.NRES_CPH);
        }
        if (state.equalsIgnoreCase("NY")) {
            total -= Constants.NY_DISCOUNT;
        } else if (state.equalsIgnoreCase("CT")) {
            total -= Constants.CT_DISCOUNT;
        }
        return total;
    }

    /**
     * Overrides the toString method.
     * @return Returns the name, d.o.b, major code, major, school, credits completed, and seniority of the student
     */
    @Override
    public String toString() {
        return (super.toString() + "(tri-state:" + state + ")");
    }
}
