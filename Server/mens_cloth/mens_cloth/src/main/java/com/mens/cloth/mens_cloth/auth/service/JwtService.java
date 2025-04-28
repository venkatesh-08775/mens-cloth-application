package com.mens.cloth.mens_cloth.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "bny8erow7rudfb82t630e32ndgy3edfdwdsdvawewred8309ed3d";

    public <T>  T extractClaim(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        if(isExpired(token)){
            return false;              //Token is Expired.
        }
        return extractClaim(token,Claims::getSubject).equals(userDetails.getUsername());
    }

    public Boolean isExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

    public String accessToken(UserDetails userDetails) {
        return accessToken(userDetails, new HashMap<>());
    }

    public String accessToken(UserDetails userDetails, Map<String,Object> extraClaims){

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getLoginKey())
                .compact();
    }

    public String refreshToken(UserDetails userDetails){
        return  refreshToken(userDetails,new HashMap<>());
    }
    public String refreshToken(UserDetails userDetails, Map<String,Object> extraClaims){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+30L * 24 *60 * 60 *1000))
                .signWith(getLoginKey())
                .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getLoginKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getLoginKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
