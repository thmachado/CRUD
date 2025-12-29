package org.thmachado.repositories;

import org.thmachado.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(UUID id);
    User save(User user);
    User update(User user);
    void delete(User user);
}
