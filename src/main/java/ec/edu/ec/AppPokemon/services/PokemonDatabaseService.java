package ec.edu.ec.AppPokemon.services;

import ec.edu.ec.AppPokemon.entities.PokemonEntity;
import ec.edu.ec.AppPokemon.repositories.PokemonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonDatabaseService {
    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonDatabaseService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public PokemonEntity savePokemon(PokemonEntity pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Transactional
    public List<PokemonEntity> obtenerTodosLosPokemones() {
        return pokemonRepository.findAll();
    }

    public boolean existsById(int id) {
        return pokemonRepository.existsById(id);
    }

    public PokemonEntity obtenerPokemonConDetalles(int id) {
        PokemonEntity pokemon = pokemonRepository.findById(id).orElse(null);
        return pokemon;
    }

    public long countPokemons() {
        return pokemonRepository.count(); // Devuelve la cantidad total de Pokémon almacenados
    }

    public List<PokemonEntity> buscarPorNombreComienzaCon(String letra) {
        return pokemonRepository.findByNameIgnoreCaseStartingWith(letra); // Busca Pokémon que comienzan con la letra, sin importar mayúsculas/minúsculas
    }


    @Transactional
    public PokemonEntity buscarPorNombre(String nombre){
        return pokemonRepository.buscarPorNombre(nombre);
    }
}
