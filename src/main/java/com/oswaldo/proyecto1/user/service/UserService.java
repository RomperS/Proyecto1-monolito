package com.oswaldo.proyecto1.user.service;

import com.oswaldo.proyecto1.exception.UserNotFoundException;
import com.oswaldo.proyecto1.exception.UsernameAlreadyUsedException;
import com.oswaldo.proyecto1.mapper.UserMapper;
import com.oswaldo.proyecto1.user.dto.UserRequest;
import com.oswaldo.proyecto1.user.dto.UserResponse;
import com.oswaldo.proyecto1.user.model.User;
import com.oswaldo.proyecto1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> findAll() {
        return UserMapper.modelsToResponses(userRepository.findAll());
    }

    public UserResponse findById(Integer id) {
        return UserMapper.modelToResponse(getModel(id));
    }

    public List<UserResponse> findByUsernameContaining(String username) {
        return UserMapper.modelsToResponses(userRepository.findByUsernameContainingIgnoreCase(username));
    }

    public UserResponse findByUsername(String username) {
        return UserMapper.modelToResponse(userRepository.findByUsername(username).orElseThrow(() -> new
                UserNotFoundException("User with username: " + username + " not found"))
        );
    }

    public List<UserResponse> findByName(String name) {
        return UserMapper.modelsToResponses(userRepository.findByName(name));
    }

    @Transactional
    public UserResponse save(User userModel) {
        if (userRepository.existsByUsername(userModel.getUsername())) {
            throw new UsernameAlreadyUsedException("Username " + userModel.getUsername() + " already used");
        }

        User model = new User();

        model.setUsername(userModel.getUsername());
        model.setName(userModel.getName());
        userRepository.save(model);

        return UserMapper.modelToResponse(userRepository.save(userModel));
    }

    @Transactional
    public UserResponse update(UserRequest userRequest, Integer id) {
        if (userRepository.existsByUsernameAndIdIsNot(userRequest.getUsername(), id)) {
            throw new UsernameAlreadyUsedException("Username " + userRequest.getUsername() + " is already taken by other user");
        }

        User model = getModel(id);

        model.setUsername(userRequest.getUsername());
        model.setName(userRequest.getName());

        return UserMapper.modelToResponse(userRepository.save(model));
    }

    public UserResponse partialUpdate(UserRequest userRequest, Integer id) {

        User model = getModel(id);

        if (userRequest.getName() != null && !userRequest.getName().isEmpty()) {
            model.setName(userRequest.getName());
        }

        if (userRequest.getUsername() != null && !userRequest.getUsername().isEmpty()) {
            if (userRepository.existsByUsernameAndIdIsNot(userRequest.getUsername(), id)) {
                throw new UsernameAlreadyUsedException("Username " + userRequest.getUsername() + " is already taken by other user");
            }

            model.setUsername(userRequest.getUsername());
        }

        return UserMapper.modelToResponse(userRepository.save(model));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private User getModel(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new
                UserNotFoundException("User with id: " + id + " not found")
        );
    }

}
