package com.example.rpg.repository;

import com.example.rpg.model.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Long> {
}