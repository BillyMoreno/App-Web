package ec.edu.ec.AppPokemon.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class AbilityDetail {
    private String name;
    private String url;

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Habilidades {" +
                "Nombre ='" + name + '\'' +
                ", URL ='" + url + '\'' +
                '}';
    }
}
