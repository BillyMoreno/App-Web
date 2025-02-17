package ec.edu.ec.AppPokemon.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {
    @Id
    private int id;
    private String name;
    private int baseExperience;
    private int height;
    private int weight;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    private List<AbilityEntity> abilities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sprites_id", referencedColumnName = "id")
    private SpritesEntity sprites;

    public PokemonEntity() {
    }

    public PokemonEntity(int id, String name, int baseExperience, int height, int weight) {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
        this.height = height;
        this.weight = weight;
    }

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

    public List<AbilityEntity> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityEntity> abilities) {
        this.abilities = abilities;
    }

    public SpritesEntity getSprites() {
        return sprites;
    }

    public void setSprites(SpritesEntity sprites) {
        this.sprites = sprites;
    }

    @Override
    public String toString() {
        return "Pokemon\n" +
                "Id= " + id + "\n" +
                "Nombre = '" + name + '\''  + "\n" +
                "Experiencia base = " + baseExperience +  "\n" +
                "Altura = " + height  + "\n" +
                "Peso = " + weight  + "\n" +
                "Habilidades = " + abilities.toString()  + "\n" +
                "Sprites = " + sprites.toString()  + "\n" +
                "\n";
    }
}
