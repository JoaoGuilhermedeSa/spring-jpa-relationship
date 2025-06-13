package com.example.rpg.controller;

import com.example.rpg.model.Guild;
import com.example.rpg.repository.GuildRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guilds")
public class GuildController {

    private final GuildRepository repo;

    public GuildController(GuildRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Guild create(@RequestBody Guild g) {
        return repo.save(g);
    }

    @GetMapping
    public List<Guild> list() {
        return repo.findAll();
    }
}