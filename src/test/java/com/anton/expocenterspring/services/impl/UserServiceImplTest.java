package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final Long ID = 1L;
    private static final String USERNAME = "anton";
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(ID);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(user).thenReturn(null);

        UserPrincipal principal = (UserPrincipal) userService.loadUserByUsername(USERNAME);

        assertNotNull(principal);
        assertEquals(user.getId(), principal.getUser().getId());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(USERNAME));

        verify(userRepository, times(2)).findByUsername(anyString());
    }

    @Test
    void saveTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User returnedUser = userService.save(user);

        assertNotNull(returnedUser);
        assertEquals(user.getId(), returnedUser.getId());

        verify(userRepository).save(any(User.class));
    }
}