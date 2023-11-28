package com.example.Othello2.authserver.service;

import com.example.Othello2.authserver.dto.difficult.DifficultRequest;
import com.example.Othello2.authserver.dto.difficult.DifficultResponse;
import com.example.Othello2.authserver.entity.DifficultEntity;
import com.example.Othello2.authserver.mapper.DifficultMapper;
import com.example.Othello2.authserver.repository.DifficultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class DifficultService {
    private final DifficultRepository difficultRepository;

    public List<DifficultResponse> getList(Long userId){
        return difficultRepository.findAllByUserId(userId).stream()
                .map(DifficultMapper::getResponseFromEntity)
                .sorted(Comparator.comparing(DifficultResponse::getDepth))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DifficultResponse> exchange(List<DifficultRequest> requestList, Long userId){
        difficultRepository.deleteAllByUserId(userId);
        List<DifficultEntity> difficultEntities = requestList.stream()
                .map(DifficultMapper::getEntityFromInput)
                .sorted(Comparator.comparing(DifficultEntity::getDepth))
                .collect(Collectors.toList());

        for (DifficultEntity difficultEntity : difficultEntities){
            difficultEntity.setUserId(userId);
        }

        difficultRepository.saveAll(difficultEntities);

        return difficultEntities.stream()
                .map(DifficultMapper::getResponseFromEntity)
                .sorted(Comparator.comparing(DifficultResponse::getDepth))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DifficultResponse> reset(Long userId){
        difficultRepository.deleteAll();
        List<DifficultEntity> difficultEntities = new ArrayList<>();
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(1)
                        .userId(userId)
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(3)
                        .userId(userId)
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(5)
                        .userId(userId)
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(7)
                        .userId(userId)
                        .build()
        );
        difficultRepository.saveAll(difficultEntities);
        return difficultEntities.stream()
                .map(DifficultMapper::getResponseFromEntity)
                .sorted(Comparator.comparing(DifficultResponse::getDepth))
                .collect(Collectors.toList());
    }
}
