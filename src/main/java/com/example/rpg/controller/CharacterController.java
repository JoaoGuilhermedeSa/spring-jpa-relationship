package com.example.rpg.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpg.model.Character;
import com.example.rpg.model.Guild;
import com.example.rpg.model.Inventory;
import com.example.rpg.model.Quest;
import com.example.rpg.repository.CharacterRepository;
import com.example.rpg.repository.GuildRepository;
import com.example.rpg.repository.QuestRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterRepository characterRepo;
    private final GuildRepository guildRepo;
    private final QuestRepository questRepo;

    public CharacterController(CharacterRepository characterRepo, GuildRepository guildRepo, QuestRepository questRepo) {
        this.characterRepo = characterRepo;
        this.guildRepo = guildRepo;
        this.questRepo = questRepo;
    }

    @PostMapping
    public Character create(@RequestBody Character character) {
        return characterRepo.save(character);
    }

    @GetMapping
    public List<Character> list() {
        return characterRepo.findAll();
    }

    @GetMapping("/{id}")
    public Character get(@PathVariable("id") Long id) {
        return characterRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        characterRepo.deleteById(id);
    }

    @PostMapping("/{id}/inventory")
    public Character addInventory(@PathVariable("id") Long id, @RequestBody Inventory inventory) {
        Character c = characterRepo.findById(id).orElseThrow();
        c.setInventory(inventory);
        return characterRepo.save(c);
    }

    @PostMapping("/{id}/quests")
    public Quest addQuest(@PathVariable("id") Long id, @RequestBody Quest quest) {
        Character c = characterRepo.findById(id).orElseThrow();
        quest.setCharacter(c);
        return questRepo.save(quest);
    }

    @PostMapping("/{charId}/guilds/{guildId}")
    @Transactional
    public Character joinGuild(@PathVariable("charId") Long charId, @PathVariable("guildId") Long guildId) {
        Character c = characterRepo.findById(charId).orElseThrow();
        Guild g = guildRepo.findById(guildId).orElseThrow();
        c.getGuilds().add(g);
        return c;
    }
}