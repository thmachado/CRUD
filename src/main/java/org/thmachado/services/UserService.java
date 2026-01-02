package org.thmachado.services;

import org.thmachado.dto.UserCreateDTO;
import org.thmachado.dto.UserResponseDTO;
import org.thmachado.models.User;
import org.thmachado.repositories.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getUsers(){
        return this.userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(UUID id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        return toResponseDTO(user);
    }

    public UserResponseDTO create(UserCreateDTO userDTO) {
        User user = new User(userDTO.firstname, userDTO.lastname, userDTO.email);
        this.userRepository.save(user);
        return toResponseDTO(user);
    }

    public UserResponseDTO update(UUID id, UserCreateDTO userDTO) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        user.changeFirstname(userDTO.firstname);
        user.changeLastname(userDTO.lastname);
        user.changeEmail(userDTO.email);

        this.userRepository.update(user);
        return toResponseDTO(user);
    }

    public void delete(UUID id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        this.userRepository.delete(user);
    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.uuid = user.getUuid();
        userDTO.firstname = user.getFirstname();
        userDTO.lastname = user.getLastname();
        userDTO.email = user.getEmail();
        return userDTO;
    }
}
