package roster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RosterTest {

    @Test
    void add_international_true() {
        International student = new International(new Profile("Doe", "John", new Date("2/14/2000")), Major.CS, 60, true);

    }
}