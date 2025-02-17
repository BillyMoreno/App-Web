package ec.edu.ec.AppPokemon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sprites")
public class SpritesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "front_default")
    private String frontDefault;

    @Column(name = "front_shiny")
    private String frontShiny;

    @Column(name = "back_default")
    private String backDefault;

    @Column(name = "back_shiny")
    private String backShiny;

    public SpritesEntity() {
    }

    public SpritesEntity(String frontDefault, String frontShiny, String backDefault, String backShiny) {
        this.frontDefault = frontDefault;
        this.frontShiny = frontShiny;
        this.backDefault = backDefault;
        this.backShiny = backShiny;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    @Override
    public String toString() {
        return "Sprites{" +
                "Frente='" + frontDefault + '\'' +
                '}';
    }
}
