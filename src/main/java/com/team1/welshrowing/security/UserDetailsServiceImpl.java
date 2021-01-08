package com.team1.welshrowing.security;

import java.util.Optional;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Code adapted from examples at https://www.websparrow.org/spring/spring-boot-spring-security-with-jpa-authentication-and-mysql [Accessed: 25 November 2020]
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserReadService userReadService;

    @Autowired
    @Lazy
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
//import com.team1.welshrowing.repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepo jpaUserRepository;
//    @Autowired
//    private JpaUserRolesRepository jpaUserRolesRepository;
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//
//        System.out.println("password encoded = " + encoder.encode("password"));
//
//
//        Optional<User> user = jpaUserRepository.findByUserName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        } else {
//            System.out.println("User = " + user);
//
//            Optional<User> userRoles = jpaUserRepository.findByUserName(username);
//            return new UserDetailsImpl();
//        }
//    }
//
//
//}