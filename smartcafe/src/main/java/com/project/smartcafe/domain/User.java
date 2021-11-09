package com.project.smartcafe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "security_user")
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> roles;

    @Column(name = "column")
    private LocalDateTime createdAt;

    public User(String firstName, String lastName, String username, String email, String password, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = Arrays.asList(role);
        this.firstName = firstName;
        this.lastName = lastName;

        createdAt = LocalDateTime.now();
    }

    //private LocalDateTime lastUpdated;
    //private LocalDateTime lastLoggedIn;

}
