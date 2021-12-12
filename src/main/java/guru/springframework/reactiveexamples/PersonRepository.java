package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author alejandrolopez
 */
public interface PersonRepository {

    Mono<Person> getById(String id);

    Flux<Person> findAll();
}
