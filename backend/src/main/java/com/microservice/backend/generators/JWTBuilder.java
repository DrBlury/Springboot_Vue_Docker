package com.microservice.backend.generators;

import com.microservice.backend.entity.UserPrivileges;
import com.microservice.backend.repos.UserPrivilegesRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
public class JWTBuilder {

    @Value("authorization.jwt.secret")
    String secretJWT;

    @Autowired
    UserPrivilegesRepository userPrivilegesRepository;

    public String generateJWTToken(String username) throws UnsupportedEncodingException {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MILLISECOND, 90000);
        //c.add(Calendar.DAY_OF_YEAR, 2);

        UserPrivileges userPrivileges = userPrivilegesRepository.findByUsername(username);

        String s = Jwts.builder()
                .setSubject(username)
                .setId("6f2614b0-b855-4d5e-8ad1-9fb3e3f84f26")
                .setIssuedAt(new Date())
                .setExpiration(c.getTime())
                .claim("admin", userPrivileges.isAdmin())
                .claim("moderator", userPrivileges.isModerator())
                .signWith(SignatureAlgorithm.HS256, secretJWT.getBytes("UTF-8"))
                .compact();

        return s;
    }

    public boolean checkJWTToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretJWT.getBytes("UTF-8"))
                    .parseClaimsJws(token);
                            //OK, we can trust this JWT
        return true;
        } catch (IllegalArgumentException | SignatureException | UnsupportedEncodingException | MalformedJwtException e) {
            //don't trust the JWT!
            return false;
        }
    }
}
