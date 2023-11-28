package com.example.Othello2.authserver.dto.difficult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DifficultResponse {
    private Long id;
    private Integer depth;
}
