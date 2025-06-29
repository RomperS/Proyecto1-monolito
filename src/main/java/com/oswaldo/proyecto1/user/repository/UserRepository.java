package com.oswaldo.proyecto1.user.repository;

import com.oswaldo.proyecto1.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameContainingIgnoreCase(String username);
    Optional<User> findByUsername(String username);
    List<User> findByName(String name);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndIdIsNot(String username, Integer id);
}
