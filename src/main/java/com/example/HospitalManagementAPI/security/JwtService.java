    package com.example.HospitalManagementAPI.security;

    // generate and validate toker
    import com.example.HospitalManagementAPI.entity.User;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.io.Decoders;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;
    import java.security.Key;
    import java.util.Date;


    // generate and validate toker
    @Service
    public class JwtService {

        @Value("${jwt.secret}")
        private String secret;

        private Key getSignInKey(){
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public String generateToken(User user) {

            return Jwts.builder()
                    .subject(user.getUsername())
                    .claim("role", user.getRole().name())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(getSignInKey())
                    .compact();
        }

        public String getUsername(String token) {
            return extractAllClaims(token).getSubject();
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parser()
                    //.verifyWith(getSignInKey())
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }

    }
