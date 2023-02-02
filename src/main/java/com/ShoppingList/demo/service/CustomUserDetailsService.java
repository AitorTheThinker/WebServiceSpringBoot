package com.ShoppingList.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShoppingList.demo.dto.UserDTO;
import com.ShoppingList.demo.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserDTO customer = userRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(customer.getUserName()).password(customer.getPassword()).authorities("ADMIN").build();
        return user;
    }
}
