package vip.yazilim.p2g.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.p2g.web.exception.RoleException;
import vip.yazilim.p2g.web.service.IRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vip.yazilim.spring.utils.exception.DatabaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Emre Sen - 25.05.2019
 * @contact maemresen07@gmail.com
 */
public class UserPrinciple implements UserDetails {

    @Autowired
    private IRoleService roleService;
    
    private final User user;

    public UserPrinciple(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        try {
            //TODO: fix get default role
            authorityList.add(new SimpleGrantedAuthority(roleService.getDefaultRole().toString()));
        } catch (DatabaseException | RoleException e) {
            e.printStackTrace();
        }

        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
