package com.ridge.geervliet.novi.dekookassistent;

import com.ridge.geervliet.novi.dekookassistent.dto.input.UserInputDto;
import com.ridge.geervliet.novi.dekookassistent.exception.ResourceNotFoundException;
import com.ridge.geervliet.novi.dekookassistent.model.User;
import com.ridge.geervliet.novi.dekookassistent.repository.UserRepository;
import com.ridge.geervliet.novi.dekookassistent.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("createUser: should encode password and save user")
    void createUser_savesWithEncodedPassword() {
        UserInputDto input = mock(UserInputDto.class);
        when(input.getFirstName()).thenReturn("Jan");
        when(input.getLastName()).thenReturn("Jansen");
        when(input.getUsername()).thenReturn("jjansen");
        when(input.getPassword()).thenReturn("plainPW");
        when(input.getEmail()).thenReturn("jan@example.com");
        when(input.getRole()).thenReturn(User.Role.USER);

        when(passwordEncoder.encode("plainPW")).thenReturn("encodedPW");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.createUser(input);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captor.capture());
        User saved = captor.getValue();

        assertEquals("Jan", saved.getFirstName());
        assertEquals("Jansen", saved.getLastName());
        assertEquals("jjansen", saved.getUsername());
        assertEquals("encodedPW", saved.getPassword());
        assertEquals("jan@example.com", saved.getEmail());
        assertEquals(User.Role.USER, saved.getRole());
    }

    @Test
    @DisplayName("getAllUsers: should return list from repository")
    void getAllUsers_returnsList() {
        User u1 = new User(); u1.setUsername("u1");
        User u2 = new User(); u2.setUsername("u2");
        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertTrue(result.contains(u1));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findByUsername: when found should return user")
    void findByUsername_found() {
        User u = new User(); u.setUsername("found");
        when(userRepository.findByUsername("found")).thenReturn(Optional.of(u));

        User result = userService.findByUsername("found");

        assertEquals(u, result);
        verify(userRepository, times(1)).findByUsername("found");
    }

    @Test
    @DisplayName("findByUsername: when not found should throw ResourceNotFoundException")
    void findByUsername_notFound() {
        when(userRepository.findByUsername("notfound")).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> userService.findByUsername("notfound"));

        assertTrue(ex.getMessage().contains("User not found"));
    }

    @Test
    @DisplayName("deleteUser: when exists should call delete")
    void deleteUser_exists() {
        when(userRepository.existsById(5L)).thenReturn(true);

        userService.deleteUser(5L);

        verify(userRepository, times(1)).deleteById(5L);
    }

    @Test
    @DisplayName("deleteUser: when not exists should throw ResourceNotFoundException")
    void deleteUser_notExists() {
        when(userRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(99L));
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("deleteByUsername: when exists should deleteByUsername")
    void deleteByUsername_exists() {
        when(userRepository.existsByUsername("user1")).thenReturn(true);

        userService.deleteByUsername("user1");

        verify(userRepository, times(1)).deleteByUsername("user1");
    }

    @Test
    @DisplayName("deleteByUsername: when not exists should throw ResourceNotFoundException")
    void deleteByUsername_notExists() {
        when(userRepository.existsByUsername("nope")).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteByUsername("nope"));
        verify(userRepository, never()).deleteByUsername(anyString());
    }

    @Test
    @DisplayName("loadUserByUsername: when found returns UserDetails with roles")
    void loadUserByUsername_found() {
        User user = new User();
        user.setUsername("authuser");
        user.setPassword("pw");
        user.setRole(User.Role.ADMIN);

        when(userRepository.findByUsername("authuser")).thenReturn(Optional.of(user));

        UserDetails details = userService.loadUserByUsername("authuser");

        assertEquals("authuser", details.getUsername());
        assertEquals("pw", details.getPassword());
        // role should be prefixed internally by Spring Security when using roles(...)
        assertFalse(details.getAuthorities().isEmpty());
    }

    @Test
    @DisplayName("loadUserByUsername: when not found throws UsernameNotFoundException")
    void loadUserByUsername_notFound() {
        when(userRepository.findByUsername("missing")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("missing"));
    }
}