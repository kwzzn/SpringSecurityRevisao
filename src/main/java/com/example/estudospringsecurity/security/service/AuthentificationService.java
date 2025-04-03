package com.example.estudospringsecurity.security.service;

import com.example.estudospringsecurity.repository.UserCompleteRepository;
import com.example.estudospringsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthentificationService implements UserDetailsService {

    private UserRepository repository;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new UserAdapter(user);
//    }

    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
         return repository.findByAuthentication_Username(username).orElseThrow(() -> new UsernameNotFoundException("User not found")).getAuthentication();
    }


}
