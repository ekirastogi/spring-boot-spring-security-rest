package com.ekiras.ss.config;

import com.ekiras.ss.domain.Role;
import com.ekiras.ss.domain.User;
import com.ekiras.ss.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ekansh
 * @since 2/4/16
 */
public class SSUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSUserDetailsService.class);

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            LOGGER.debug("user not found with the provided username");
            return null;
        }
        LOGGER.debug(" user from username " + user.toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(username));
    }

    private Set<GrantedAuthority> getAuthorities(String username){
        Set<Role> roles = userRepository.findRolesByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        LOGGER.debug("user authorities are " + authorities.toString());
        return authorities;
    }


}
