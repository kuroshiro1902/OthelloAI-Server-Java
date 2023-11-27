package com.example.Othello2.authserver.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
}
