package vip.yazilim.p2g.web.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vip.yazilim.p2g.web.config.security.user.CustomUserPrincipal;
import vip.yazilim.p2g.web.entity.User;

import java.util.UUID;

public class SecurityHelper {

    private static CustomUserPrincipal getUserPrinciple() {
        return (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Authentication getUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static User getUser() {
        return getUserPrinciple().getUser();
    }

    public static UUID getUserUuid() {
        return getUser().getUuid();
    }

    public static UUID getUserUuid(Authentication authentication) {
        return ((CustomUserPrincipal)authentication.getPrincipal()).getUser().getUuid();
    }

    public static String getUserDisplayName() {
        return getUser().getDisplayName();
    }

    public static String getUserDisplayName(Authentication authentication) {
        return ((CustomUserPrincipal)authentication.getPrincipal()).getUser().getDisplayName();
    }

}
