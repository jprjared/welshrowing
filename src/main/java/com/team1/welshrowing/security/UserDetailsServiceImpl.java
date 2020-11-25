package com.team1.welshrowing.security;

import java.util.Optional;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserReadService userReadService;

    @Autowired
    public UserDetailsServiceImpl(UserReadService userReadService) {
        this.userReadService = userReadService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userReadService.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
        return user.map(UserDetailsImpl::new).get();
    }

}