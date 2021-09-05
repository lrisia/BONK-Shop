package ku.cs.models.verify;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testNullPassword() {
        Account account = new Account();
        assertFalse(account.register("Irisia", "", "12345"));
    }

    @Test
    void testNullConfirmPassword() {
        Account account = new Account();
        assertFalse(account.register("Irisia", "12345", ""));
    }

    @Test
    void testAllPasswordNull() {
        Account account = new Account();
        assertFalse(account.register("Irisia", "", ""));
    }

    @Test
    void testPasswordNotSame() {
        Account account = new Account();
        assertFalse(account.register("Irisia", "12345", "54321"));
    }

    @Test
    void testCanRegister() {
        Account account = new Account();
        assertTrue(account.register("Irisia", "12345", "12345"));
    }
}