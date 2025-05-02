package com.example.MultiAppBackend.user;

import com.example.MultiAppBackend.homizer.homizerItem.HomizerItemDto;
import com.example.MultiAppBackend.homizer.homizerItem.HomizerItemService;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageDto;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserServiceTest {

    @InjectMocks
    private MyUserService userService;

    @Mock
    private MyUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HomizerItemService homizerItemService;

    @Mock
    private HomizerStorageService homizerStorageService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup_shouldCreateUser_whenPasswordsMatch() {
        UserRegisterDto input = new UserRegisterDto();
        input.setEmail("test@example.com");
        input.setPassword("password");
        input.setPasswordRepeat("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.signup(input);

        ArgumentCaptor<MyUser> userCaptor = ArgumentCaptor.forClass(MyUser.class);
        verify(userRepository).save(userCaptor.capture());

        assertEquals("test@example.com", userCaptor.getValue().getEmail());
        assertEquals("encodedPassword", userCaptor.getValue().getPassword());
    }

    @Test
    void signup_shouldReturnNull_whenPasswordsDoNotMatch() {
        UserRegisterDto input = new UserRegisterDto();
        input.setPassword("123");
        input.setPasswordRepeat("456");

        MyUser result = userService.signup(input);

        assertNull(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void authenticate_shouldReturnUser_whenCredentialsAreValid() {
        UserLoginDto login = new UserLoginDto();
        login.setEmail("test@example.com");
        login.setPassword("password");

        MyUser user = new MyUser();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        MyUser result = userService.authenticate(login);

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("test@example.com", "password"));
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void getUserInfos_shouldReturnCorrectCounts() {
        Principal principal = () -> "test@example.com";

        when(homizerItemService.getAllHomizerItemsFromUser(principal))
                .thenReturn(List.of(new HomizerItemDto(), new HomizerItemDto()));
        when(homizerStorageService.getAllHomizerStoragesfromUser(principal))
                .thenReturn(List.of(new HomizerStorageDto()));

        UserInfosDto infos = userService.getUserInfos(principal);

        assertEquals("test@example.com", infos.getEmail());
        assertEquals(2, infos.getItemCount());
        assertEquals(1, infos.getStorageCount());
    }

    @Test
    void deleteUser_shouldDeleteItemsStoragesAndUser() {
        Principal principal = () -> "test@example.com";

        HomizerItemDto item1 = new HomizerItemDto();
        item1.setId("1L");
        HomizerItemDto item2 = new HomizerItemDto();
        item2.setId("2L");

        HomizerStorageDto storage1 = new HomizerStorageDto();
        storage1.setId("3L");

        MyUser user = new MyUser();
        user.setEmail("test@example.com");

        when(homizerItemService.getAllHomizerItemsFromUser(principal)).thenReturn(List.of(item1, item2));
        when(homizerStorageService.getAllHomizerStoragesfromUser(principal)).thenReturn(List.of(storage1));
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        userService.deleteUser(principal);

        verify(homizerItemService).deleteHomizerItemById("1L", principal);
        verify(homizerItemService).deleteHomizerItemById("2L", principal);
        verify(homizerStorageService).deleteHomizerStorageById("3L", principal);
        verify(userRepository).delete(user);
    }
}
