package com.example.chassis.dao;

import com.example.chassis.entity.GreetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<GreetingEntity, Long> {
}