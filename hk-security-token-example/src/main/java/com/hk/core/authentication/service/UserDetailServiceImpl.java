package com.hk.core.authentication.service;

import com.hk.core.authentication.security.accesstoken.TokenAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Duration;

/**
 * @author huangkai
 * @date 2019/3/7 13:58
 */
public class UserDetailServiceImpl extends TokenAuthenticationProvider implements UserDetailsService {

    public UserDetailServiceImpl(Duration expire) {
        super(expire);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return convertToTokenUserPrincipal(new SecurityUserPrincipal());
        return null;
    }
}
