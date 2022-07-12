package com.haanbhai.blogs.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String about;
    private boolean isActive;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();
}
