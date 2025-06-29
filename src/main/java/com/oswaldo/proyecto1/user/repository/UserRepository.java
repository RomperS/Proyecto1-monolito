package com.oswaldo.proyecto1.user.repository;

import com.oswaldo.proyecto1.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<UserModel> findByUsernameContainingIgnoreCase(String username);
    Optional<UserModel> findByUsername(String username);
    List<UserModel> findByName(String name);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndIdIsNot(String username, Integer id);
}
