package application.util;

import application.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String HASHING = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b: digest) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Couldn't hash the password", e);
        }
        return hashedPassword.toString();
    }

    public static boolean isValid(String password, User user) {
        User userFromDB = new User();
        byte[] salt = user.getSalt();
        userFromDB.setPassword(hashPassword(password, salt));
        userFromDB.setSalt(salt);
        return hashPassword(password, salt).equals(userFromDB.getPassword());
    }
}
