package roster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the tuitionDue() method for Internation students.
 * @author Jason Guo, Russel Rivera
 */
public class InternationalTest {

    @Test
    void tuitionDue_studyAbroad() {
        International student = new International(new Profile("Doe", "John", new Date("2/14/2000")), Major.CS, 60, true);
        assertEquals((Constants.INTER_HEALTH_FEES + Constants.FULL_UNI_FEES), student.tuitionDue(12));
    }

    @Test
    void tuitionDue_notAbroad() {
        International student = new International(new Profile("Doe", "Jane", new Date("9/29/2002")), Major.MATH, 71, false);
        assertEquals((Constants.NRES_FULL_TUITION + Constants.INTER_HEALTH_FEES + Constants.FULL_UNI_FEES + (2 * Constants.NRES_CPH)), student.tuitionDue(18));
        // 2 is the difference between enrolled credits 18 and standard credits 16
    }
}