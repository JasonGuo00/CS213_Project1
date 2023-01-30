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
        String prof1 = fname + " " + lname + " " + dob.toString();
        String prof2 = prof.fname + " " + prof.lname + " " + prof.dob.toString();
        return (prof1.compareTo(prof2));
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
