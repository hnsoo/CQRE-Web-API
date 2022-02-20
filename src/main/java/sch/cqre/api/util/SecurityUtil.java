package sch.cqre.api.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {

    public static Optional<String> getCurrentUsername() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            log.debug("There is no Authorizatgion in Security Context");
            return Optional.empty();
        }

        String username = null;
        if(authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();

        } else if(authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
}
