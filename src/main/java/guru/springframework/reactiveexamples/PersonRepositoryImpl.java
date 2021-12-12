package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author alejandrolopez
 */
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Mono<Person> getById(String id) {
        return null;
    }

    @Override
    public Flux<Person> findAll() {
        return null;
    }
}
