package com.example.rpg.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Character {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Inventory inventory;

	@ManyToMany
	@JoinTable(name = "character_guild", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "guild_id"))
	private Set<Guild> guilds = new HashSet<>();

	@OneToMany(mappedBy = "character", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Quest> quests = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Set<Guild> getGuilds() {
		return guilds;
	}

	public void setGuilds(Set<Guild> guilds) {
		this.guilds = guilds;
	}

	public List<Quest> getQuests() {
		return quests;
	}

	public void setQuests(List<Quest> quests) {
		this.quests = quests;
	}

}