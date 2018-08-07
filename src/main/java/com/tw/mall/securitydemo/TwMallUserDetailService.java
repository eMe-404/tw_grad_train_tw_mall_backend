package com.tw.mall.securitydemo;

import com.tw.mall.entity.User;
import com.tw.mall.login.JwtUser;
import com.tw.mall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class TwMallUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public TwMallUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("%s dose't exist!!!", username)));
        return new JwtUser(user);


    }
}
