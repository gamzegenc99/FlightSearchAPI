package FlightSearchAPI.flightsearchapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Date;
import java.util.function.Function;

public class JwtUtil {

    private static final String SECRET_KEY = "s3cr3tK3y!_2023@MyApp#$1212121212121212143434343434343434343434343434356565656565656565656565656787878787878787878787878787"
            + "eeeeeeeeekfdfchvckjvejkcvedjlwegdlewkldheıllgdlegdıelgdlekcoegcugercgerc"
            + "khegjkcgejkcukergcukercoıeroıclerlcblıercglıbglıcbgerlıocgoıergcerccrv"
            + "jkcgervclerblıcberlcbhlıecbıerchılerkbclkrbckbrlıcbrjbvjlrbcjlbrjlcbrjlkcb";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    
    
    private JwtUtil() {
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String generateToken(String username) {
        return Jwts.builder().subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(KEY).compact();
    }

    public static Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}