package ec.edu.ec.AppPokemon.repositories;

import ec.edu.ec.AppPokemon.entities.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {
    @Query("SELECT p FROM PokemonEntity p WHERE p.name = :nombre")
    PokemonEntity buscarPorNombre(String nombre);
    List<PokemonEntity> findByNameIgnoreCaseStartingWith(String name);  // Aquí usamos 'IgnoreCase' para no hacer distinción entre mayúsculas y minúsculas

}

