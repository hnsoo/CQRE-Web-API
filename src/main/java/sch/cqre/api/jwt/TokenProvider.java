package sch.cqre.api.jwt;

제import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.exception.ErrorCode;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private final UserDetailsService userDetailsService;

    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;


    public TokenProvider(
            UserDetailsService userDetailsService, @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.userDetailsService = userDetailsService;
        this.secret = secret;

        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    //Bean
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String userId, String email, String roles) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("uid", userId);
        claims.put("roles", roles);
        log.warn(String.valueOf(claims));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + this.tokenValidityInMilliseconds)) // set Expire Time
                .signWith(key, SignatureAlgorithm.HS512)
                // 사용할 암호화 알고리즘 + 시그니쳐에 들어갈 시크릿값
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getEmail(token));
        log.warn("토큰 인증 시도한 회원 정보 : " + userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("wrong JWT - securityException");
            throw new CustomException(ErrorCode.WRONG_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("Expired Jwt");
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("unsupport jwt");
        } catch (IllegalArgumentException e) {
            log.info("wrong jwt - illegal");
        }
        return false;
    }


    // 토큰에서 회원 정보 추출
    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().get("email", String.class);
    }

    public String getUid(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().get("uid", String.class);
    }




}
