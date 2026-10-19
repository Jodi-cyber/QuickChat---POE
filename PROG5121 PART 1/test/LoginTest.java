import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyle!!!!!!!",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(false, login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        assertEquals(false, login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "089665573"
        );

        assertEquals(false, login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(
                login.loginUser(
                        "kyl_1",
                        "Ch8&&sec@ke99!"
                )
        );
    }

    @Test
    public void testLoginFailed() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(
                login.loginUser(
                        "wrong_username",
                        "wrong_password"
                )
        );
    }

    @Test
    public void testSuccessfulLoginMessage() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Welcome John, Smith it is great to see you again.",
                login.returnLoginStatus(
                        "kyl_1",
                        "Ch8&&sec@ke99!"
                )
        );
    }

    @Test
    public void testFailedLoginMessage() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch8&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(
                        "wrong_username",
                        "wrong_password"
                )
        );
    }
}
