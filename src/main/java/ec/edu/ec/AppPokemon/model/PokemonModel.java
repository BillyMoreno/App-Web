package ec.edu.ec.AppPokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ec.edu.ec.AppPokemon.entities.*;

import java.util.List;

public class PokemonModel {
    private int id;
    private String name;

    @JsonProperty("base_experience")
    private int baseExperience;

    private int height;
    private int weight;

    private List<Ability> abilities;
    private Sprites sprites;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public PokemonEntity toEntity() {
        PokemonEntity entity = new PokemonEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setBaseExperience(this.baseExperience);
        entity.setHeight(this.height);
        entity.setWeight(this.weight);

        // Mapear habilidades
        if (this.abilities != null) {
            List<AbilityEntity> abilityEntities = this.abilities.stream()
                    .map(Ability::toEntity)
                    .toList();
            entity.setAbilities(abilityEntities);
        }

        // Mapear sprites
        if (this.sprites != null) {
            SpritesEntity spritesEntity = this.sprites.toEntity();
            entity.setSprites(spritesEntity);
        }

        return entity;
    }

    public PokemonModel(int id, String name, int baseExperience, int height, int weight, List<Ability> abilities, Sprites sprites) {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.sprites = sprites;
    }
}
