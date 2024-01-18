import java.security.*;

public class PasswordGenerator {

     static String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     static String LOWER = "abcdefghijklmnopqrstuvwxyz";
     static String DIGITS = "0123456789";
     static String SPECIAL = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
     static String generatePassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than 0");
        }

        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        // Add at least one character from each category
        password.append(getRandomChar(UPPER, random));
        password.append(getRandomChar(LOWER, random));
        password.append(getRandomChar(DIGITS, random));
        password.append(getRandomChar(SPECIAL, random));

        // Fill the remaining length with random characters
        for (int i = 4; i < length; i++) {
            String allChars = UPPER + LOWER + DIGITS + SPECIAL;
            password.append(getRandomChar(allChars, random));
        }

        // Shuffle the characters in the password
        for (int i = password.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = password.charAt(index);
            password.setCharAt(index, password.charAt(i));
            password.setCharAt(i, temp);
        }

        return password.toString();
    }

    private static char getRandomChar(String charSet, SecureRandom random) {
        int index = random.nextInt(charSet.length());
        return charSet.charAt(index);
    }

    public static void main(String[] args) {
        int passwordLength = 15;
        String password = generatePassword(passwordLength);
        System.out.println("Generated Password: " + password);
    }
}
