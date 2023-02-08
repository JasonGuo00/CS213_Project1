package roster;

/**
 * Enum Major that organizes the different majors the RosterManager can handle.  Includes information on the major's
 * course code and school.
 * @author Jason Guo, Russel Rivera
 */
public enum Major {
    CS ("01:198", "SAS"),
    MATH ("01:640", "SAS"),
    EE ("14:332", "SOE"),
    ITI ("04:547", "SC&I"),
    BAIT ("33:136", "RBS");

    public final String code;
    public final String school;

    /**
     * Enum Major can hold onto two pieces of data each: a course code and a school name.
     * @param code Course code of the major
     * @param school School that the major is offered at
     */
    Major(String code, String school) {
        this.code = code;
        this.school = school;
    }
}
