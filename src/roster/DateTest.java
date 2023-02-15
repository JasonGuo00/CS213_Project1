package roster;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void test_isValid_daysInFeb_nonLeap() {
        Date date = new Date("2/29/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_daysInApril__less31() {
        Date date = new Date("4/31/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_invalidMonth() {
        Date date = new Date("13/31/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_invalidDay() {
        Date date = new Date("3/32/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_negMonth() {
        Date date = new Date("-1/31/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_leapYear() {
        Date date = new Date("2/29/2020");
        assertTrue(date.isValid());
    }

    @Test
    public void test_isValid_validDate() {
        Date date = new Date("7/31/1999");
        assertTrue(date.isValid());
    }

    @Test
    public void test_isValid_currentDate() {
        Date date = new Date();
        assertTrue(date.isValid());
    }

    @Test
    public void test_isValid_validDate2() {
        Date date = new Date("11/30/1989");
        assertTrue(date.isValid());
    }

    @Test
    public void test_isValid_futureDate() {
        Date date = new Date("12/1/2023");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_negDay() {
        Date date = new Date("3/-1/2004");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_negYear() {
        Date date = new Date("8/18/-1");
        assertFalse(date.isValid());
    }


}