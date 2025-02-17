package ec.edu.ec.AppPokemon;


import ec.edu.ec.AppPokemon.api.PokemonServiceAPI;
import ec.edu.ec.AppPokemon.model.PokemonModel;
import ec.edu.ec.AppPokemon.services.PokemonDatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class RestFullPokemonApplication {

	private static PokemonServiceAPI pokemonService;
	private static PokemonDatabaseService pokemonDatabaseService;

	private static void abrirNavegador(String url) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			} else {
				System.out.println("No se pudo abrir el navegador automáticamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public static void main(String[] args) {
		//SpringApplication.run(RestFullPokemonApplication.class, args);


		System.setProperty("java.awt.headless", "false");

		var context = SpringApplication.run(RestFullPokemonApplication.class, args);
		abrirNavegador("http://localhost:8080/");

		pokemonService = context.getBean(PokemonServiceAPI.class);
		pokemonDatabaseService = context.getBean(PokemonDatabaseService.class);

		if (pokemonDatabaseService.countPokemons() > 0) {
			System.out.println("Los Pokémon ya están cargados en la base de datos. No es necesario volver a cargarlos.");
		} else {
			System.out.println("No hay Pokémon en la base de datos. Cargando Pokémon...");
			chargePokemons();
		}

	}

	private static void chargePokemons(){
		System.out.println("Cargando Pokemons.");
		Flux<PokemonModel> pokemonDetails = pokemonService.getAllPokemonDetails();
		pokemonDetails
				.publishOn(Schedulers.boundedElastic())
				.filter(pokemon -> !pokemonDatabaseService.existsById(pokemon.getId())) // Verificar si ya existe en la base de datos
				.doOnNext(pokemon -> pokemonDatabaseService.savePokemon(pokemon.toEntity()))
				.doOnError(error -> System.err.println("Error al procesar Pokémon: " + error.getMessage()))
				.subscribe(
						pokemon -> {},
						error -> System.err.println("Error en el flujo: " + error.getMessage()),
						() -> System.out.println("Carga de Pokémons completado.")
				);
	}



}
