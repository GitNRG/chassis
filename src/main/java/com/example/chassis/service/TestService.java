package com.example.chassis.service;

import com.example.chassis.dao.GreetingRepository;
import com.example.chassis.entity.GreetingEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    private GreetingRepository repo;

    public TestService(GreetingRepository repo) {
        this.repo = repo;
    }

    public String greeting() {
        Optional<GreetingEntity> entity = repo.findById(1L);
        return entity.isPresent() ? entity.get().getMessage() : "<no message found>";
    }
}
