package ec.edu.ec.AppPokemon.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sprites {
    @JsonProperty("front_default")
    private String frontDefault;

    @JsonProperty("front_shiny")
    private String frontShiny;

    @JsonProperty("back_default")
    private String backDefault;

    @JsonProperty("back_shiny")
    private String backShiny;

    // Getters y setters
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

    public SpritesEntity toEntity() {
        SpritesEntity entity = new SpritesEntity();
        entity.setFrontDefault(this.frontDefault);
        entity.setFrontShiny(this.frontShiny);
        entity.setBackDefault(this.backDefault);
        entity.setBackShiny(this.backShiny);
        return entity;
    }
}
