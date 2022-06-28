package com.springcore.boardtest.security;

import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "해당 사용자가 존재하지 않습니다. " + username));

        return new CustomUserDetails(userEntity);
    }
}
