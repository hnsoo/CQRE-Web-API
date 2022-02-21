package sch.cqre.api.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;


public enum Role implements GrantedAuthority {
    /*ROLE_GUEST, ROLE_USER, ROLE_MANAGER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    } */
    // 주의: 클래스로 하면 안댐
    GUEST(define.GUEST), //, "준회원"),
    USER(define.USER), //, "정회원"),
    MANAGER(define.MANAGER),//, "운영진"),
    ADMIN(define.ADMIN);//, "관리자");

    private final String authority;

    Role(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static class define {

        //오타방지용
        public static final String ADMIN = "ADMIN";
        public static final String MANAGER = "MANAGER";
        public static final String USER = "USER";
        public static final String GUEST = "GUEST";

        public static final String role_ADMIN = "ROLE_ADMIN";
        public static final String role_MANAGER = "ROLE_MANAGER";
        public static final String role_USER = "ROLE_USER";
        public static final String role_GUEST = "ROLE_GUEST";


        //hasRole 파라메타 명시
        public static String[] ONLY_ADMIN = {"ADMIN"};
        public static String[] ONLY_ADMIN_MANAGER = {"ADMIN", "MANAGER"};
        public static String[] WITHOUT_GUEST = {"ADMIN", "MANAGER", "USER"};
        public static String[] ALL_ACCOUNT = {"ADMIN", "MANAGER", "USER", "GUEST"};
    }
}

