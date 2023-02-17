package roster;

public class International extends NonResident {
    private boolean isStudyAbroad;
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

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
    private double fullTime(int creditsEnrolled) {
        double total = Constants.FULL_UNI_FEES;
        total += Constants.NRES_FULL_TUITION;
        total += Constants.INTER_HEALTH_FEES;
        if(creditsEnrolled > Constants.STD_CREDITS) {
            total += ((creditsEnrolled - Constants.STD_CREDITS) * Constants.NRES_CPH);
        }
        return total;
    }

    @Override
    public boolean isValid(int creditsEnrolled) {
        if(!isStudyAbroad) {
            return (creditsEnrolled <= Constants.MAX_CREDITS) && (creditsEnrolled >= Constants.FULL_TIME_CREDITS);
        }
        else {
            return (creditsEnrolled <= Constants.FULL_TIME_CREDITS) && (creditsEnrolled >= Constants.MIN_CREDITS);
        }
    }
    @Override
    public boolean isResident() {
        return false;
    }

    public boolean isStudyAbroad() {
        return isStudyAbroad;
    }

    @Override
    public String getStatus() {
        if (isStudyAbroad) {
            return "International studentstudy abroad";
        } else {
            return "International student";
        }
    }
}
