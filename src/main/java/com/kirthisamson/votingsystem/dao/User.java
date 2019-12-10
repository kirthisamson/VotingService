package com.kirthisamson.votingsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity class for the User table
 *
 * This class leverages the @Data @NoArgsConstructor @AllArgsConstructor and the @Builder annotation from the
 * Project Lombok to minimize boilerplate code.
 * @see <a href="https://projectlombok.org/">https://projectlombok.org/</a>
 *
 * Created by Kirthi Samson Chilkuri
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String email;
}
