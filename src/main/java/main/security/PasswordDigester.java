package main.security;

import com.google.common.base.Throwables;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Michal
 * 2015-06-08.
 */
public class PasswordDigester {
    static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Throwables.propagate(e);
        }
    }

    public static String getDigested(String string) {
        try {
            return new String(md.digest(string.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            Throwables.propagate(e);
        }
        return null;
    }
}
