package roster;

public class NonResident extends Student {
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
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
        if(creditsEnrolled > Constants.STD_CREDITS) {
            total += ((creditsEnrolled - Constants.STD_CREDITS) * Constants.NRES_CPH);
        }
        return total;
    }

    @Override
    public String getStatus() {
        return "Resident";
    }
}
