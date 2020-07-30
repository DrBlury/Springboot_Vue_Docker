package com.microservice.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivileges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name= "username")
    String username;

    @Column(name= "isAdmin")
    boolean isAdmin;

    @Column(name= "isModerator")
    boolean isModerator;
}
