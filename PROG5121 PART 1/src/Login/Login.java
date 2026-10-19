package Login;

public class Login {

    private final String username;
    private final String password;
    private final String cellPhoneNumber;
    private final String firstName;
    private final String lastName;

    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {

        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (char character : password.toCharArray()) {

            if (Character.isUpperCase(character)) {
                hasCapitalLetter = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }

    public boolean checkCellPhoneNumber() {

        String regex = "^\\+27\\d{1,10}$";

        return cellPhoneNumber != null
                && cellPhoneNumber.matches(regex);
    }

    public String registerUser() {

        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {

            return "Cell phone number is incorrectly formatted or does not contain international code.";
        }

        return "User successfully registered.";
    }

    public boolean loginUser(String enteredUsername,
                             String enteredPassword) {

        return username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername,
                                    String enteredPassword) {

        if (loginUser(enteredUsername, enteredPassword)) {

            return "Welcome " + firstName + ", "
                    + lastName + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }

    public static class Main {

        public static void main(String[] args) {
            Login user = new Login("John", "Smith", "kyl_1", "Ch8&&sec@ke99!", "+27838968976");
            System.out.println("REGISTRATION");
            System.out.println("----------------------------");
            System.out.println("Username valid: " + user.checkUserName());
            System.out.println("Password valid: " + user.checkPasswordComplexity());
            System.out.println("Cell phone valid: " + user.checkCellPhoneNumber());
            System.out.println(user.registerUser());
            System.out.println();
            System.out.println("LOGIN");
            System.out.println("----------------------------");
            System.out.println(user.returnLoginStatus("kyl_1", "Ch8&&sec@ke99!"));
        }
    }
}

