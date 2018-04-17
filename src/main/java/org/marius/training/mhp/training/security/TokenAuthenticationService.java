package org.marius.training.mhp.training.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public final class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsASecret";
    private static final String HEADER_STRING = "Authorization";

    /**
     * private constructor, to avoid instanciation
     */
    private TokenAuthenticationService() {
    }

    public static void addAuthentication(final HttpServletResponse res, final String username,
                                         final Collection<? extends GrantedAuthority> privileges) throws JsonProcessingException, IOException {
        final String JWT = Jwts //
                .builder() //
                .setSubject(username) //
                .claim("privileges", privileges) //
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)) //
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        res.addHeader("Authorization", JWT);
    }

    public static Authentication getAuthentication(final HttpServletRequest request) {
        Authentication returnValue = null;

        final String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // parse the token.
            final String user = Jwts //
                    .parser() //
                    .setSigningKey(SECRET)//
                    .parseClaimsJws(token)//
                    .getBody()//
                    .getSubject();
            if (user != null) {
                @SuppressWarnings("unchecked")
                final Collection<LinkedHashMap<String, String>> auths = (Collection<LinkedHashMap<String, String>>) Jwts //
                        .parser() //
                        .setSigningKey(SECRET)//
                        .parseClaimsJws(token)//
                        .getBody().get("privileges");

                final List<GrantedAuthority> parsedAuth = new ArrayList<>();
                if (auths != null) {
                    for (final LinkedHashMap<String, String> map : auths) {
                        parsedAuth.add(new SimpleGrantedAuthority(map.get("authority")));
                    }
                }
                returnValue = new UsernamePasswordAuthenticationToken(user, null, parsedAuth);
            }
        }
        return returnValue;
    }
}
