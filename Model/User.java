package com.example.usersmanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, message = "Name must be at least 4 characters")
    @Column(columnDefinition = "varchar(20) not null check(length(name)>=4)")
    private String name;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "Username must be at least 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique check(length(username)>=4)")
    private String username;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "^(user|admin)$", message = "Role must be either user or admin")
    @Column(columnDefinition = "varchar(5) not null check(role='user' or role='admin')")
    private String role;
    @NotNull(message = "Age cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
