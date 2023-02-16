package roster;

public class TriState extends NonResident{
    private String state;
    public TriState(Profile profile, Major major, int creditCompleted, String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled >= Constants.FULL_TIME_CREDITS) {
            return fullTime(creditsEnrolled);
        }
        return partTime(creditsEnrolled);
    }

    @Override
    public boolean isResident() {
        return false;
    }

    private double partTime(int creditsEnrolled) {
        double total = Constants.PART_UNI_FEES;
        total += (Constants.NRES_CPH * creditsEnrolled);
        return total;
    }
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
}