package com.example.usersmanagementsystem.Repository;

import com.example.usersmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByUsernameAndPassword(String email, String password);

    User findUserByEmail(String email);

    List<User> findUsersByRole(String role);

    List<User> findUsersByAgeGreaterThanEqual(Integer age);
}
