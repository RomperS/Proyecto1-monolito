package com.oswaldo.proyecto1.mapper;

import com.oswaldo.proyecto1.user.dto.UserResponse;
import com.oswaldo.proyecto1.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse modelToResponse(User userModel) {
        UserResponse response = new UserResponse();

        response.setId(userModel.getId());
        response.setUsername(userModel.getUsername());
        response.setName(userModel.getName());

        return response;
    }

    public static List<UserResponse> modelsToResponses(List<User> userModels) {
        return userModels.stream().map(UserMapper::modelToResponse).collect(Collectors.toList());
    }
}
