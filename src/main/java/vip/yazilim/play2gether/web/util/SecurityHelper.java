package vip.yazilim.play2gether.web.util;

import vip.yazilim.play2gether.web.config.security.UserPrinciple;
import vip.yazilim.play2gether.web.entity.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Emre Sen - 25.05.2019
 * @contact maemresen07@gmail.com
 */
public class SecurityHelper {

    public static UserPrinciple getUserPrinciple() {
        return (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    public static SystemUser getSystemUser() {
        return getUserPrinciple().getSystemUser();
    }

    public static boolean hasAnyRole(String... roleNames) {
        for (String roleName : roleNames) {
            if (hasRole(roleName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasRole(String roleName) {
        for (GrantedAuthority grantedAuthority : getUserPrinciple().getAuthorities()) {
            String authority = grantedAuthority.getAuthority();
            System.out.println(String.format("Comparing: Authority: %s, Role: %s", authority, roleName));
            if (authority.equals(roleName)) {
                System.out.println("Matched Authority: " + authority);
                return true;
            }
        }
        return false;
    }
}
