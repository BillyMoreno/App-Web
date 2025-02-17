package ec.edu.ec.AppPokemon.model;

import java.util.List;

public class PokemonList {
    private int count;
    private List<PokemonModel> results;

    // Getters y setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PokemonModel> getResults() {
        return results;
    }

    public void setResults(List<PokemonModel> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PokemonList{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
