package org.thmachado.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thmachado.dto.UserCreateDTO;
import org.thmachado.dto.UserResponseDTO;
import org.thmachado.repositories.InMemoryUserRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    private final String firstname = "Thiago";
    private final String lastname = "Machado";
    private final String email = "thiago.wimps@gmail.com";

    @BeforeEach
    void setUp(){
        this.userService = new UserService(new InMemoryUserRepository());
    }

    @Test
    void shouldListEmptyUsers(){
        assertEquals(0, this.userService.getUsers().size());
    }

    @Test
    void shouldListUsers(){
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = this.firstname;
        userDTO.lastname = this.lastname;
        userDTO.email = this.email;

        this.userService.create(userDTO);
        assertEquals(1, this.userService.getUsers().size());
    }

    @Test
    void shouldUserNotFound() {
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = this.firstname;
        userDTO.lastname = this.lastname;
        userDTO.email = this.email;

        UserResponseDTO userCreated = this.userService.create(userDTO);
        UserResponseDTO userFound = this.userService.findById(userCreated.uuid);
        assertEquals(userCreated.uuid, userFound.uuid);

        assertThrows(IllegalStateException.class, () -> {
            this.userService.findById(UUID.randomUUID());
        });
    }

    @Test
    void shouldFindUserById() {
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = this.firstname;
        userDTO.lastname = this.lastname;
        userDTO.email = this.email;

        UserResponseDTO userCreated = this.userService.create(userDTO);
        UserResponseDTO userFound = this.userService.findById(userCreated.uuid);
        assertEquals(userCreated.uuid, userFound.uuid);
    }

    @Test
    void shouldUpdateUser() {
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = this.firstname;
        userDTO.lastname = this.lastname;
        userDTO.email = this.email;

        UserResponseDTO userCreated = this.userService.create(userDTO);

        UserCreateDTO userUpdateDTO = new UserCreateDTO();
        userUpdateDTO.firstname = "Gustavo";
        userUpdateDTO.lastname = "Gomez";
        userUpdateDTO.email = "gustavogomez@email.com";

        UserResponseDTO userUpdated = this.userService.update(userCreated.uuid, userUpdateDTO);
        assertEquals("Gustavo", userUpdated.firstname);
    }

    @Test
    void shouldDeleteUser() {
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = this.firstname;
        userDTO.lastname = this.lastname;
        userDTO.email = this.email;

        UserResponseDTO userCreated = this.userService.create(userDTO);
        this.userService.delete(userCreated.uuid);

        assertThrows(IllegalStateException.class, () -> {
            this.userService.findById(userCreated.uuid);
        });
    }
}
