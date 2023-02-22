package roster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Roster's add() method
 * @author Jason Guo, Russel Rivera
 */
public class RosterTest {

    @Test
    void add_international_true() {
        Roster roster = new Roster();
        Student student = makeStudent(new Profile("Doe", "John", new Date("2/14/2000")), getMajor("CS"), 60, 0, checkValidity("2/14/2000", "CS", "60"));
        assertTrue(roster.add(student));
    }

    @Test
    void add_international_false() {
        Roster roster = new Roster();
        Student student = makeStudent(new Profile("Doe", "Jane", new Date("7/32/2000")), getMajor("MATH"), 75, 1, checkValidity("7/32/2000", "MATH", "75"));
        assertFalse(roster.add(student));
    }
    @Test
    void add_tri_true() {
        Roster roster = new Roster();
        Student student = makeStudent(new Profile("Doe", "John", new Date("4/12/1997")), getMajor("BAIT"), 12, 2, checkValidity("4/12/1997", "BAIT", "12"));
        assertTrue(roster.add(student));
    }

    @Test
    void add_tri_false() {
        Roster roster = new Roster();
        Student student = makeStudent(new Profile("Doe", "Jane", new Date("11/12/2003")), getMajor("ITI"), -1, 1, checkValidity("11/12/2003", "ITI", "-1"));
        assertFalse(roster.add(student));
    }

    /**
     * Create the student.
     * @param profile Profile of the student.
     * @param major Student's declared major.
     * @param credits Credits the student has completed.
     * @param id Value indicating the type of student.
     * @param valid If the student is valid or not.
     * @return International, TriState, or null.
     */
    private Student makeStudent(Profile profile, Major major, int credits, int id, boolean valid) {
        if(!valid) {
            return null;
        }
        switch(id) {
            case Constants.INTER_NOT_ABROAD:
                return new International(profile, major, credits, false);
            case Constants.INTER_ABROAD:
                return new International(profile, major, credits, true);
            case Constants.TRISTATE_NY:
                return new TriState(profile, major, credits, "NY");
            case Constants.TRISTATE_CT:
                return new TriState(profile, major, credits, "CT");
            default:
                return null;
        }
    }

    /**
     * Check that the parameters for the student is valid
     * @param date Date of Birth of student.
     * @param major Student's declared major.
     * @param cr Credits the student has completed.
     * @return true or false.
     */
    private boolean checkValidity(String date, String major, String cr) {
        Date d = new Date(date);
        if (!d.isValid()) {return false;
        } else if (d.isUnderage()) {return false;
        } else if (!cr.matches("-?[0-9]+")) {return false;
        } else if (cr.matches("-[0-9]+")) {return false;
        } else if (getMajor(major) == null) {return false;
        }return true;
    }

    /**
     * Obtain the major enum based off of string.
     * @param major Student's declared major.
     * @return Corresponding major enum, or null.
     */
    private Major getMajor(String major) {
        if(major.equalsIgnoreCase("CS")) {return Major.CS;}
        else if(major.equalsIgnoreCase("MATH")) {return Major.MATH;}
        else if(major.equalsIgnoreCase("EE")) {return Major.EE;}
        else if(major.equalsIgnoreCase("ITI")) {return Major.ITI;}
        else if(major.equalsIgnoreCase("BAIT")) {return Major.BAIT;}
        else {return null;}
    }
}
