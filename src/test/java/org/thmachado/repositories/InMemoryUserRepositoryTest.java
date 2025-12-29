package org.thmachado.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thmachado.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserRepositoryTest {
    private final String firstname = "Thiago";
    private final String lastname = "Machado";
    private final String email = "thiago.wimps@gmail.com";
    private InMemoryUserRepository inMemoryUserRepository;

    @BeforeEach
    void setUp(){
        this.inMemoryUserRepository = new InMemoryUserRepository();
    }

    @Test
    void shouldListEmptyUsers(){
        assertEquals(0, this.inMemoryUserRepository.findAll().size());
    }

    @Test
    void shouldListOneUser(){
        this.inMemoryUserRepository.save(new User(this.firstname, this.lastname, this.email));
        List<User> users = this.inMemoryUserRepository.findAll();
        assertEquals(1, users.size());
        assertEquals(this.lastname, users.get(0).getLastname());
    }

    @Test
    void shouldListUserByUuid(){
        this.inMemoryUserRepository.save(new User(this.firstname, this.lastname, this.email));
        List<User> users = this.inMemoryUserRepository.findAll();
        UUID uuid = users.get(0).getUuid();
        Optional<User> user = this.inMemoryUserRepository.findById(uuid);
        assertEquals(uuid, user.get().getUuid());
        assertEquals(this.email, user.get().getEmail());
    }

    @Test
    void shouldNotListUserByUuid(){
        this.inMemoryUserRepository.save(new User(this.firstname, this.lastname, this.email));
        Optional<User> user = this.inMemoryUserRepository.findById(UUID.randomUUID());
        assertFalse(user.isPresent());
    }

    @Test
    void shouldUpdateUser(){
        String firstname = "Ademir";
        String lastname = "Da Guia";
        User user = new User(this.firstname, this.lastname, this.email);
        this.inMemoryUserRepository.save(user);
        user.changeFirstname(firstname);
        user.changeLastname(lastname);
        User userUpdated = this.inMemoryUserRepository.update(user);

        assertEquals(this.email, userUpdated.getEmail());
        assertEquals(firstname, userUpdated.getFirstname());
        assertEquals(lastname, userUpdated.getLastname());
    }

    @Test
    void shouldDeleteUser(){
        User user = new User(this.firstname, this.lastname, this.email);
        this.inMemoryUserRepository.save(user);
        this.inMemoryUserRepository.delete(user);
        assertEquals(0, this.inMemoryUserRepository.findAll().size());
    }
}
