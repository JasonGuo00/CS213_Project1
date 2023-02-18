package roster;

/**
 * International is a subclass of NonResident, used to classify students who don't live in the U.S.
 * @author Jason Guo, Russel Rivera
 */
public class International extends NonResident {
    private boolean isStudyAbroad;

    /**
     * Constructor for International object.
     * @param profile Profile of the student.
     * @param major Student's declared major.
     * @param creditCompleted Number of credits that the student has completed.
     * @param isStudyAbroad Whether the student is participating in a study abroad program.
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Calculate the full-time tuition the student must pay based on the number of credits they've enrolled in.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    private double fullTime(int creditsEnrolled) {
        double total = Constants.FULL_UNI_FEES;
        total += Constants.NRES_FULL_TUITION;
        total += Constants.INTER_HEALTH_FEES;
        if(creditsEnrolled > Constants.STD_CREDITS) {
            total += ((creditsEnrolled - Constants.STD_CREDITS) * Constants.NRES_CPH);
        }
        return total;
    }

    /**
     * Classify student as either full-time or participating in a study abroad program to retrieve the correct tuition due.
     * @param creditsEnrolled Number of credits the student has enrolled in.
     * @return The total amount of tuition that the student will owe.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(isStudyAbroad) {
            return Constants.INTER_HEALTH_FEES + Constants.FULL_UNI_FEES;
        }
        else if(creditsEnrolled >= Constants.FULL_TIME_CREDITS) {
            return fullTime(creditsEnrolled);
        }
        return -1;
    }

    /**
     * Override of the isValid in the sueprclass. Determines if the number of credits enrolled by the student is valid.
     * @param creditsEnrolled Number of credits that student has enrolled in.
     * @return true or false.
     */
    @Override
    public boolean isValid(int creditsEnrolled) {
        if(!isStudyAbroad) {
            return (creditsEnrolled <= Constants.MAX_CREDITS) && (creditsEnrolled >= Constants.FULL_TIME_CREDITS);
        }
        else {
            return (creditsEnrolled <= Constants.FULL_TIME_CREDITS) && (creditsEnrolled >= Constants.MIN_CREDITS);
        }
    }

    /**
     * Overrides isResident in the superclass.  For International students, always returns false.
     * @return false.
     */
    @Override
    public boolean isResident() {
        return false;
    }

    /**
     * Returns whether the International student is enrolled in a study abroad program.
     * @return true or false.
     */
    public boolean isStudyAbroad() {
        return isStudyAbroad;
    }

    /**
     * Overrides the toString method.
     * @return Returns the name, d.o.b, major code, major, school, credits completed, and seniority of the student
     */
    @Override
    public String toString() {
        if (isStudyAbroad) {
            return (super.toString() + "(international:study abroad)");
        } else {
            return (super.toString() + "(international)");
        }
    }
}
