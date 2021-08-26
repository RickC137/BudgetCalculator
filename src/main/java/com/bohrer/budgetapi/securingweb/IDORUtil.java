package com.bohrer.budgetapi.securingweb;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;

/**
 * https://cheatsheetseries.owasp.org/cheatsheets/Insecure_Direct_Object_Reference_Prevention_Cheat_Sheet.html
 * This is the example implementation to prevent idors as done by owasp
 */
public class IDORUtil {

    // salt needed for randomness
    @Value("${security.salt.value:testingValue")
    private static final String SALT_VALUE;

    public static String computeFrontEndIdentifier(String id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String frontEndId = null;
        if(id != null && !id.trim().isEmpty()) {
            String tmp = SALT_VALUE + id;
            MessageDigest digest = MessageDigest.getInstance("sha1");
            byte[] hash = digest.digest(tmp.getBytes("utf-8"));
            frontEndId = DatatypeConverter.printHexBinary(hash);
        }

        return frontEndId;
    }
    
}
