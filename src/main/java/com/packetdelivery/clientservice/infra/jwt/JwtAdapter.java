package com.packetdelivery.clientservice;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtAdapter implements IEncoder {

    private String secretKey;

    public JwtAdapter() {
        String env = System.getenv("JWT_SECRET_KEY");
        this.secretKey = env != null ? env : "any_key";
    }

    @Override
    public String encode(String value) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withSubject(value).sign(algorithm);
    }

    @Override
    public String decode(String token) throws InvalidParamException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new InvalidParamException("Invalid token");
        }
    }
}
