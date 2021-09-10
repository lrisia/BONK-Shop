package ku.cs.models.verify;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Account01Test {

    @Test
    void testNullPassword() {
        Account01 account01 = new Account01();
        assertFalse(account01.register("Irisia", "", "12345"));
    }

    @Test
    void testNullConfirmPassword() {
        Account01 account01 = new Account01();
        assertFalse(account01.register("Irisia", "12345", ""));
    }

    @Test
    void testAllPasswordNull() {
        Account01 account01 = new Account01();
        assertFalse(account01.register("Irisia", "", ""));
    }

    @Test
    void testPasswordNotSame() {
        Account01 account01 = new Account01();
        assertFalse(account01.register("Irisia", "12345", "54321"));
    }

    @Test
    void testCanRegister() {
        Account01 account01 = new Account01();
        assertTrue(account01.register("Irisia", "12345", "12345"));
    }
}