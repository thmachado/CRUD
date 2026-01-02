package org.thmachado.controllers;

import org.thmachado.dto.UserCreateDTO;
import org.thmachado.dto.UserResponseDTO;
import org.thmachado.services.UserService;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public List<UserResponseDTO> index(){
        return this.userService.getUsers();
    }

    public UserResponseDTO store(String firstname, String lastname, String email){
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = firstname;
        userDTO.lastname = lastname;
        userDTO.email = email;
        return this.userService.create(userDTO);
    }

    public UserResponseDTO update(UUID id, String firstname, String lastname, String email){
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.firstname = firstname;
        userDTO.lastname = lastname;
        userDTO.email = email;
        return this.userService.update(id, userDTO);
    }

    public void destroy(UUID id){
        this.userService.delete(id);
    }
}
