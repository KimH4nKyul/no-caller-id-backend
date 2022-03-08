package com.tx0x.nocalleridbackend.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

@Service
public class JwtToken {

    @Value("${secret.key}")
    private String SECRET_KEY;

    @Value("${secret.identifier}")
    private String SECRET_IDENTIFIER;

    public String createToken(String info, long expire) {
        // subject is path
        if(expire <= 0) throw new RuntimeException("[JwtToken] expire is greater than 0");

        // ready
        String issuer = info.split(SECRET_IDENTIFIER)[0];
        String domain = info.split(SECRET_IDENTIFIER)[1];

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .claim("id", issuer)
                .claim("email", domain)
//                .setSubject(subject)
                .signWith(signKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    public Claims verificationJwtToken(String jwtToken) throws UnsupportedEncodingException {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
}
