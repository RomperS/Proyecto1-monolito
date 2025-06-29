package com.oswaldo.proyecto1.user.repository;

import com.oswaldo.proyecto1.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<UserModel> findByUsernameContainingIgnoreCase(String username);
    List<UserModel> findByName(String name);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndIdIsNot(String username, Integer id);
}
