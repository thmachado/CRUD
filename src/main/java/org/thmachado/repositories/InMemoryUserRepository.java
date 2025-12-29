package org.thmachado.repositories;

import org.thmachado.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository{
    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(user -> user.getUuid().equals(id))
                .findFirst();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        Optional<User> userOptional = findById(user.getUuid());
        if(userOptional.isEmpty()){
            throw new IllegalStateException("User not found");
        }

        User userFound = userOptional.get();
        userFound.changeFirstname(user.getFirstname());
        userFound.changeLastname(user.getLastname());
        userFound.changeEmail(user.getEmail());
        return userFound;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
