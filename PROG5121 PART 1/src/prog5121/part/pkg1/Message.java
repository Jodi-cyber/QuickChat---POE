package prog5121.part.pkg1;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Message {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Set<String> USED_IDS = new HashSet<>();

    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String message;
    private final String messageHash;

    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        String id;

        synchronized (USED_IDS) {
            do {
                id = String.format(
                    "%010d",
                    RANDOM.nextLong(1_000_000_000L, 10_000_000_000L)
                );
            } while (!USED_IDS.add(id));
        }

        return id;
    }

    public boolean checkMessageID() {
        return messageID.matches("\\d{10}");
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted "
            + "or does not contain an international code. "
            + "Please correct the number and try again.";
    }

    public String checkMessageLength() {
        if (message == null || message.length() > 250) {
            return "Please enter a message of less than 250 characters.";
        }

        return "Message ready to send.";
    }

    public String createMessageHash() {
        String trimmed = message == null ? "" : message.trim();

        if (trimmed.isEmpty()) {
            return messageID.substring(0, 2) + ":"
                + messageNumber + ":";
        }

        String[] words = trimmed.split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1]
            .replaceAll("[^a-zA-Z0-9]", "");

        return (messageID.substring(0, 2) + ":"
            + messageNumber + ":"
            + firstWord + lastWord).toUpperCase(Locale.ROOT);
    }

    public String SentMessage() {
        return "Message ready for processing.";
    }

    public String printMessages() {
        return "Message ID: " + messageID
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipient
            + "\nMessage: " + message;
    }

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
}