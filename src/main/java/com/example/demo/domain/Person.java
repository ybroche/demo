package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime modified = LocalDateTime.now();

    private LocalDateTime lastLogin = LocalDateTime.now();

    private String uuid = UUID.randomUUID().toString();

    private Boolean isActive = true;

    @OneToMany(mappedBy = "person")
    private Set<Phone> phones = new HashSet<>();

    public Person(String name, String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
