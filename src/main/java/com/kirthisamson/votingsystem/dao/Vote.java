package com.kirthisamson.votingsystem.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity class for the Vote table
 *
 * This class leverages the @Data @NoArgsConstructor @AllArgsConstructor and the @Builder annotation from the
 * Project Lombok to minimize boilerplate code.
 * @see <a href="https://projectlombok.org/">https://projectlombok.org/</a>
 *
 * @Author Kirthi Samson Chilkuri
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int userId;

    @NotNull
    private int questionId;

    @NotNull
    private boolean vote;
}
