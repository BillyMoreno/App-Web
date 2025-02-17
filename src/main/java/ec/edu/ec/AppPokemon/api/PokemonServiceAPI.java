package ec.edu.ec.AppPokemon.api;

import ec.edu.ec.AppPokemon.entities.Pokemonv2;
import ec.edu.ec.AppPokemon.model.PokemonList;
import ec.edu.ec.AppPokemon.model.PokemonModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PokemonServiceAPI {

    private final WebClient webClient;

    public PokemonServiceAPI(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://pokeapi.co/api/v2").build();
    }

    public Mono<Pokemonv2> getPokemonByName(String name) {
        String cleanedName = name.trim();

        return webClient.get()
                .uri("/pokemon/{name}", cleanedName)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        clientResponse -> Mono.error(new RuntimeException("Pokémon no encontrado"))
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Error del servidor"))
                )
                .bodyToMono(Pokemonv2.class);
    }

    /**
     * Obtiene una lista de Pokémon con paginación.
     *
     * @param offset Inicio de la lista.
     * @param limit  Número máximo de resultados por consulta.
     * @return Un Mono que contiene un objeto PokemonList.
     */
    public Mono<PokemonList> getAllPokemon(int offset, int limit) {
        // Validar que offset y limit sean positivos
        if (offset < 0 || limit <= 0) {
            return Mono.error(new IllegalArgumentException("Offset y limit deben ser mayores o iguales a 0"));
        }

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon")
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        clientResponse -> Mono.error(new RuntimeException("No se pudieron recuperar los Pokémon"))
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Error del servidor"))
                )
                .bodyToMono(PokemonList.class);
    }

    /**
     * Obtiene la información de todos los Pokémon.
     *
     * @return Un Flux que contiene objetos Pokemonv2.
     */
    public Flux<PokemonModel> getAllPokemonDetails() {
        int startId = 1;
        int endId = 1350;
        // Crear un contador atómico para rastrear los errores
        AtomicInteger errorCounter = new AtomicInteger(0);

        return Flux.range(startId, endId - startId + 1)
                .parallel() // Habilitar paralelismo
                .runOn(Schedulers.parallel()) // Ejecutar en hilos paralelos
                .flatMap(id -> webClient.get()
                        .uri("/pokemon/{id}", id)
                        .retrieve()
                        .bodyToMono(PokemonModel.class)
                        .retryWhen(Retry.backoff(3, Duration.ofMillis(500)))// Recolecta todos los detalles de los Pokémon
                        .doOnError(e -> {
                            System.out.println("Error con ID: " + id);
                            errorCounter.incrementAndGet(); // Incrementa el contador de errores
                        })
                        .onErrorResume(e -> Mono.empty())
                    )
                .sequential()
                .doFinally(signal -> {
                    // Al final del flujo, imprime el número total de errores
                    System.out.println("Número total de IDs con error: " + errorCounter.get());
        });
    }
}
