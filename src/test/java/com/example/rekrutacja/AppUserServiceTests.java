package com.example.rekrutacja;

import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.repository.AppUserRepository;
import com.example.rekrutacja.service.AppUserService;
import com.example.rekrutacja.utils.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AppUserServiceTests {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getUserById_UserExists_ReturnsUser() {
        // Arrange
        Long userId = 1L;
        AppUser mockUser = new AppUser(userId, "John", "123456789", "Doe", "john.doe", "password", "john.doe@example.com", null, true, null);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        AppUser result = appUserService.getUserById(userId);

        // Assert
        assertEquals(mockUser, result);
    }

    @Test
    void getUserById_UserNotFound_ThrowsResourceNotFoundException() {
        // Arrange
        Long userId = 999L;

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> appUserService.getUserById(userId));
    }


    @Test
    void getAllUsersByNameAndSurname_UsersFound_ReturnsUsersList() {
        // Arrange
        String name = "John";
        String surname = "Doe";
        List<AppUser> expectedUsers = Arrays.asList(
                new AppUser(1L, "John", "123456789", "Doe", "john.doe", "password", "john.doe@example.com", null, true, null),
                new AppUser(2L, "John", "987654321", "Smith", "john.smith", "password", "john.smith@example.com", null, true, null)
        );

        when(appUserRepository.findAppUsersByNameAndSurname(name, surname)).thenReturn(expectedUsers);

        // Act
        List<AppUser> result = appUserService.getAllUsersByNameAndSurname(name, surname);

        // Assert
        assertEquals(expectedUsers, result);
    }

    @Test
    void getAllUsersByNameAndSurname_NoUsersFound_ReturnsEmptyList() {
        // Arrange
        String name = "Nonexistent";
        String surname = "User";

        when(appUserRepository.findAppUsersByNameAndSurname(name, surname)).thenReturn(Arrays.asList());

        // Act
        List<AppUser> result = appUserService.getAllUsersByNameAndSurname(name, surname);

        // Assert
        assertTrue(result.isEmpty());
    }
}
