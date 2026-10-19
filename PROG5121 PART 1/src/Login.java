public class Login {

    private String username;
    private String password;
    private String cellphoneNumber;
    private boolean loggedIn;

    public boolean checkUserName(String username) {
        return username != null
                && username.length() <= 5
                && username.contains("_");
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                hasCapital = true;
            } else if (Character.isDigit(character)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellphoneNumber) {
        return cellphoneNumber != null
                && cellphoneNumber.matches("\\+27\\d{9}");
    }

    public String registerUser(String username, String password,
                               String cellphoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, "
                    + "please ensure that your username contains "
                    + "an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellphoneNumber)) {
            return "Cell phone number is incorrectly formatted "
                    + "or does not contain the international code.";
        }

        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;

        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        loggedIn = this.username != null
                && this.password != null
                && this.username.equals(username)
                && this.password.equals(password);

        return loggedIn;
    }

    public String returnLoginStatus() {
        if (loggedIn) {
            return "Welcome, it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}