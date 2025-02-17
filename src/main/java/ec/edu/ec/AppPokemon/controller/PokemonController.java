package ec.edu.ec.AppPokemon.controller;

import ec.edu.ec.AppPokemon.api.PokemonServiceAPI;
import ec.edu.ec.AppPokemon.entities.PokemonEntity;
import ec.edu.ec.AppPokemon.entities.Pokemonv2;
import ec.edu.ec.AppPokemon.model.PokemonList;
import ec.edu.ec.AppPokemon.model.PokemonModel;
import ec.edu.ec.AppPokemon.services.PokemonDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonDatabaseService pokemonDatabaseService;
    private final PokemonServiceAPI pokemonService;

    @Autowired
    public PokemonController(PokemonServiceAPI pokemonService, PokemonDatabaseService pokemonDatabaseService) {
        this.pokemonService = pokemonService;
        this.pokemonDatabaseService = pokemonDatabaseService;
    }

    @GetMapping("/{name}")
    public Mono<Pokemonv2> getPokemon(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }


    /**
     * Endpoint para obtener una lista de Pokémon con paginación.
     *
     * @param offset Inicio de la lista (por defecto 0).
     * @param limit  Número máximo de resultados por consulta (por defecto 20).
     * @return Un Mono que contiene un objeto PokemonList.
     */
    @GetMapping("/get-all-pokemon")
    public Mono<PokemonList> getAllPokemon(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        return pokemonService.getAllPokemon(offset, limit);
    }

    /**
     * Endpoint para obtener detalles de todos los Pokémon (usando paginación interna).
     *
     * @return Un Flux que contiene objetos Pokemonv2.
     */
    @GetMapping("/get-all-pokemon-details")
    public Flux<PokemonModel> getAllPokemonDetails() {
        return pokemonService.getAllPokemonDetails();
    }

    @GetMapping("/")
    public String index() {
        return "index";
        // Esto debería mapear a src/main/resources/templates/index.html
    }

    @GetMapping("/viewAllPokemons")
    public String viewAllPokemons(Model model) {
        List<PokemonEntity> pokemons = pokemonDatabaseService.obtenerTodosLosPokemones();

        // Formatear el nombre de cada Pokémon para que comience con mayúscula
        pokemons.forEach(pokemon -> {
            String formattedName = capitalizeFirstLetter(pokemon.getName());
            pokemon.setName(formattedName);  // Modificar el nombre en el objeto
        });

        model.addAttribute("pokemons", pokemons);
        return "viewAllPokemons"; // Carga la plantilla HTML
    }

    @GetMapping("/searchFor")
    public String searchFor() {
        return "searchFor";  // Cargar la página de búsqueda
    }

    @PostMapping("/search")
    public String searchByName(@RequestParam String searchTerm, Model model) {
        List<PokemonEntity> pokemons;

        if (isNumeric(searchTerm)) {
            // Si el término es un número (ID)
            int id = Integer.parseInt(searchTerm);
            pokemons = List.of(pokemonDatabaseService.obtenerPokemonConDetalles(id));
        } else {
            // Si es un nombre, buscar Pokémon que empiecen con la letra dada (mayúscula o minúscula)
            pokemons = pokemonDatabaseService.buscarPorNombreComienzaCon(searchTerm);
        }

        // Formatear el nombre de cada Pokémon para que empiece con mayúscula
        pokemons.forEach(pokemon -> {
            String formattedName = capitalizeFirstLetter(pokemon.getName());
            pokemon.setName(formattedName);  // Modificar el nombre en el objeto
        });

        model.addAttribute("pokemons", pokemons);
        return "viewAllPokemons";  // Mostrar los resultados de la búsqueda
    }


    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para capitalizar solo la primera letra del nombre
    private String capitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }




}
