package com.example.usersmanagementsystem.Controller;

import com.example.usersmanagementsystem.Model.User;
import com.example.usersmanagementsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.updateUser(user, id);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @GetMapping("/check-username-password")
    public ResponseEntity checkUsernameAndPassword(@RequestBody Map<String, String> credentials){
        userService.checkUsernameAndPassword(credentials.get("username"), credentials.get("password"));
        return ResponseEntity.status(200).body("Valid username and password");
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get-by-role/{role}")
    public ResponseEntity getUsersByRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.getUsersByRole(role));
    }

    @GetMapping("/get-by-age/{age}")
    public ResponseEntity getUsersWithSpecificAgeOrAbove(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.getUsersWithSpecificAgeOrAbove(age));
    }
}
