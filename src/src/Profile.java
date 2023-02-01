package src;

public class Profile implements Comparable<Profile>{

    private String lname;
    private String fname;
    private Date dob;

    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }
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

    @Override
    public String toString() {
        return (fname + " " + lname + " " + dob.toString());
    }

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
