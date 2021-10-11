package ku.cs.models.verify;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testLocalDateTime() {
        Account account = new Account("Irisia", "1234", "Ton");
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDataTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm:ss"));
        assertEquals(loginDataTime, account.getLoginDateTime());
    }

    @Test
    void testCheckAccount() {
        Account account = new Account("Irisia", "1234", "Ton");
        assertTrue(account.checkAccount("Irisia"));
    }

    @Test
    void testCheckAccountNotFound() {
        Account account = new Account("Irisia", "1234", "Ton");
        assertFalse(account.checkAccount("Mellon"));
    }

    @Test
    void testToCsv() {
        Account account = new Account("Irisia", "1234", "Ton");
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDataTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm:ss"));
        String expected = "Account,Irisia,1234,Ton,false," + loginDataTime;
        assertEquals(expected, account.toCsv());
    }

}