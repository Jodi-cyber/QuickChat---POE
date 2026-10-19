package prog5121.part.pkg1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChat {

    private static final Scanner INPUT = new Scanner(System.in);

    private static final List<Message> SENT_MESSAGES = new ArrayList<>();
    private static final List<Message> STORED_MESSAGES = new ArrayList<>();

    private static int totalSent = 0;
    private static int messageCount = 0;

    public static void main(String[] args) {

        // Replace this demonstration check with Part 1 login validation.
        if (!loginSuccessful()) {
            System.out.println("Login failed. Please try again.");
            return;
        }

        System.out.println("Welcome to QuickChat.");

        boolean running = true;

        while (running) {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String choice = INPUT.nextLine();

            switch (choice) {
                case "1" -> sendMessages();

                case "2" -> System.out.println("Coming Soon.");

                case "3" -> {
                    System.out.println("Total messages sent: " + totalSent);
                    System.out.println("Goodbye!");
                    running = false;
                }

                default -> System.out.println("Invalid option. Choose 1, 2 or 3.");
            }
        }
    }

    private static boolean loginSuccessful() {
        System.out.println("Please log in using your Part 1 account.");
        System.out.print("Username: ");
        String username = INPUT.nextLine();

        System.out.print("Password: ");
        String password = INPUT.nextLine();

        // Demonstration only: replace with your real Part 1 login method.
        return !username.trim().isEmpty()
            && !password.trim().isEmpty();
    }

    private static void sendMessages() {
        System.out.print("How many messages would you like to enter? ");

        int number;

        try {
            number = Integer.parseInt(INPUT.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number.");
            return;
        }

        if (number < 1) {
            System.out.println("Please enter at least one message.");
            return;
        }

        for (int i = 0; i < number; i++) {
            System.out.println("\nMessage " + (i + 1));

            System.out.print("Recipient number (+27 followed by 9 digits): ");
            String recipient = INPUT.nextLine().trim();

            System.out.print("Enter your message: ");
            String text = INPUT.nextLine();

            Message current = new Message(messageCount, recipient, text);

            if (!current.checkMessageID()) {
                System.out.println("Invalid message ID.");
                i--;
                continue;
            }

            if (!current.checkRecipientCell().startsWith(
                    "Cell phone number successfully")) {
                System.out.println(current.checkRecipientCell());
                i--;
                continue;
            }

            if (!current.checkMessageLength().equals(
                    "Message ready to send.")) {
                System.out.println(
                    "Please enter a message of less than 250 characters."
                );
                i--;
                continue;
            }

            System.out.println("\n" + current.printMessages());

            System.out.println("\n1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message to send later");
            System.out.print("Choose an option: ");

            String action = INPUT.nextLine();

            switch (action) {
                case "1" -> {
                    SENT_MESSAGES.add(current);
                    totalSent++;
                    messageCount++;
                    System.out.println("Message successfully sent.");
                }

                case "2" -> {
                    messageCount++;
                    System.out.println("Press 0 to delete the message.");
                }

                case "3" -> {
                    STORED_MESSAGES.add(current);
                    messageCount++;
                    saveMessagesToJSON();
                    System.out.println("Message successfully stored.");
                }

                default -> {
                    System.out.println(
                            "Invalid choice. Message was not sent."
                    );
                    i--;
                }
            }
        }

        System.out.println("Total messages sent: " + totalSent);
    }

    private static void saveMessagesToJSON() {
        StringBuilder json = new StringBuilder("[\n");

        for (int i = 0; i < STORED_MESSAGES.size(); i++) {
            Message m = STORED_MESSAGES.get(i);

            json.append("  {\n");
            json.append("    \"messageID\": \"")
                .append(escapeJSON(m.getMessageID())).append("\",\n");
            json.append("    \"messageHash\": \"")
                .append(escapeJSON(m.getMessageHash())).append("\",\n");
            json.append("    \"recipient\": \"")
                .append(escapeJSON(m.getRecipient())).append("\",\n");
            json.append("    \"message\": \"")
                .append(escapeJSON(m.getMessage())).append("\"\n");
            json.append("  }");

            if (i < STORED_MESSAGES.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]\n");

        try {
            Files.write(
                Path.of("messages.json"),
                json.toString().getBytes(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            System.out.println("Could not save the messages file.");
        }
    }

    private static String escapeJSON(String value) {
        return value.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
    }
}