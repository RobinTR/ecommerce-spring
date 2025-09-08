package org.robn.ecommerce.auth.util;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;

import javax.crypto.SecretKey;

@UtilityClass
public class CryptoUtil {

    public static SecretKey getSecretKey(final String key) {
        final byte[] keyBytes = Decoders.BASE64.decode(key);

        return Keys.hmacShaKeyFor(keyBytes);
    }

}
