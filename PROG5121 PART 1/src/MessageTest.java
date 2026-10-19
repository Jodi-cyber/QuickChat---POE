public class MessageTest {

    public static void main(String[] args) {
        System.out.println("Starting Message Tests...");

        testMessageID();
        testMessageRecipient();
        testMessageText();

        System.out.println("Message Tests Completed.");
    }

    public static void testMessageID() {
        String messageID = "1234567890";

        if (messageID.length() == 10) {
            System.out.println("PASS: Message ID has 10 digits.");
        } else {
            System.out.println("FAIL: Message ID must have 10 digits.");
        }
    }

    public static void testMessageRecipient() {
        String recipient = "+27718693002";

        if (recipient.startsWith("+27")) {
            System.out.println("PASS: Recipient uses the South African country code.");
        } else {
            System.out.println("FAIL: Check the recipient country code.");
        }
    }

    public static void testMessageText() {
        String message = "Hello, this is a test message.";

        if (!message.isEmpty() && message.length() <= 250) {
            System.out.println("PASS: Message text is within the limit.");
        } else {
            System.out.println("FAIL: Message text is empty or too long.");
        }
    }
}
