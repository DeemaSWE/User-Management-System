package com.example.usersmanagementsystem.Service;

import com.example.usersmanagementsystem.Api.ApiException;
import com.example.usersmanagementsystem.Model.User;
import com.example.usersmanagementsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user){
        if(userRepository.existsUserByEmail(user.getEmail()))
            throw new ApiException("User with this email already exists");

        if(userRepository.existsUserByUsername(user.getUsername()))
            throw new ApiException("User with this username already exists");

        userRepository.save(user);
    }

    public void updateUser(User updatedUser, Integer id){
        User user = userRepository.findUserById(id);

        if(user == null)
            throw new ApiException("User not found");

        if(userRepository.existsUserByEmail(updatedUser.getEmail()) && !user.getEmail().equals(updatedUser.getEmail()))
            throw new ApiException("User with this email already exists");

        if(userRepository.existsUserByUsername(updatedUser.getUsername()) && !user.getUsername().equals(updatedUser.getUsername()))
            throw new ApiException("User with this username already exists");

        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);

        if(user == null)
            throw new ApiException("User not found");

        userRepository.delete(user);
    }

    public void checkUsernameAndPassword(String username, String password){
        if(!userRepository.existsUserByUsernameAndPassword(username, password))
            throw new ApiException("Invalid username or password");
    }

    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);

        if(user == null)
            throw new ApiException("User not found");

        return user;
    }

    public List<User> getUsersByRole(String role){
        List<User> userList = userRepository.findUsersByRole(role);

        if(userList.isEmpty())
            throw new ApiException("User not found");

        return userList;
    }

    public List<User> getUsersWithSpecificAgeOrAbove(Integer age){
        List<User> userList = userRepository.findUsersByAgeGreaterThanEqual(age);

        if(userList.isEmpty())
            throw new ApiException("User not found");

        return userList;
    }
}
