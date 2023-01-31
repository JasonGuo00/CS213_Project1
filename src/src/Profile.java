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
        if(lname.compareTo(prof.lname) < Constants.EQUALS) {
            return Constants.LESSER;
        }
        else if(lname.compareTo(prof.lname) > Constants.EQUALS) {
            return Constants.GREATER;
        }
        // Same last name
        else {
            if(fname.compareTo(prof.fname) < Constants.EQUALS) {
                return Constants.LESSER;
            }
            else if(fname.compareTo(prof.fname) > Constants.EQUALS) {
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
            if (fname.equals(profile.fname) && lname.equals(profile.lname) && dob.equals(profile.dob)) {
                return true;
            }
        }
        return false;
    }
}
