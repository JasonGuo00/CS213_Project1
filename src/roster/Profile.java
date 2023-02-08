package roster;

/**
 * Profile contains the first and last name of the Student, as well as their date of birth.
 * A student's Profile is the main way to identify students.
 * @author Jason Guo, Russel Rivera
 */
public class Profile implements Comparable<Profile>{

    private String lname;
    private String fname;
    private Date dob;

    /**
     * Constructor for a Profile object.
     * @param lname Last name of the student
     * @param fname First name of the student
     * @param dob Date of birth of the student
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Override of compareTo.
     * Compares the portfolios.  First looks at the last name lexicographically.  Then moves to first name if necessary.
     * Finally it checks the date of births if the two students have the same name.
     * @param prof the object to be compared.
     * @return Returns -1, 0, 1 depending on the names and d.o.b of the students compared
     */
    @Override
    public int compareTo(Profile prof) {
        String lname_case = lname.toLowerCase();
        String fname_case = fname.toLowerCase();
        if(lname_case.compareTo(prof.lname.toLowerCase()) < Constants.EQUALS) {
            return Constants.LESSER;
        }
        else if(lname_case.compareTo(prof.lname.toLowerCase()) > Constants.EQUALS) {
            return Constants.GREATER;
        }
        // Same last name
        else {
            if(fname_case.compareTo(prof.fname.toLowerCase()) < Constants.EQUALS) {
                return Constants.LESSER;
            }
            else if(fname_case.compareTo(prof.fname.toLowerCase()) > Constants.EQUALS) {
                return Constants.GREATER;
            }
            // Same first and last name, result depends only on dob
            else {
                return dob.compareTo(prof.dob);
            }
        }
    }

    /**
     * Overrides the toString method.
     * @return Returns the first name, last name, and date of birth of the Student as one string.
     */
    @Override
    public String toString() {
        return (fname + " " + lname + " " + dob.toString());
    }

    /**
     * Overrides the equals method.
     * @param obj Object to be compared to.
     * @return Returns true if the two students are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profile) {
            Profile profile = (Profile) obj;
            if (fname.equalsIgnoreCase(profile.fname) && lname.equalsIgnoreCase(profile.lname) && dob.equals(profile.dob)) {
                return true;
            }
        }
        return false;
    }
}
