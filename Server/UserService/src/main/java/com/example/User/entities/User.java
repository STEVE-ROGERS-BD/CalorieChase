package com.example.User.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String dp;
    private String gender;
    private String age;
    private String weight;
    private String height;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.dp = null;
        this.gender = null;
        this.age = null;
        this.weight = null;
        this.height = null;
        this.role = "USER";
    }
}
