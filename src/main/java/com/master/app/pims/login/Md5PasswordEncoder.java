package com.master.app.pims.login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Md5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        throw new UnsupportedOperationException("Salt is required for encoding. Use encode(rawPassword, salt) instead.");
    }

    public String encode(CharSequence rawPassword, String salt) {
        return md5Hash(rawPassword + salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        throw new UnsupportedOperationException("Salt is required for matching. Use matches(rawPassword, encodedPassword, salt) instead.");
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword, String salt) {
        return md5Hash(rawPassword + salt).equals(encodedPassword);
    }

    private String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
