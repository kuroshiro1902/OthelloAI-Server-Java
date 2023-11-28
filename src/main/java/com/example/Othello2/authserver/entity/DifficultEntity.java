package com.example.Othello2.authserver.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_difficults")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DifficultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Integer depth;

    @Column(name = "user_id")
    private Long userId;
}
