package com.anton.expocenterspring.services;

import com.anton.expocenterspring.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);
}
