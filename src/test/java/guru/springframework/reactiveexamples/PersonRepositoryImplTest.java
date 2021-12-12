package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void getByIdBlock() {
        final Mono<Person> personMon = personRepository.getById(1);
        final Person person = personMon.block();
        System.out.println(person.toString());
    }

    @Test
    void getByIdSubscribe() {
        final Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void getByIdMapFunction() {
        final Mono<Person> personMono = personRepository.getById(1);
        personMono
                .map(Person::getFirstName)
                .subscribe(firstName -> System.out.println("from map: " + firstName));
    }

    @Test
    void fluxTestBlockFirst() {
        final Flux<Person> personFlux = personRepository.findAll();
        final Person person = personFlux.blockFirst();
        System.out.println(person.toString());
    }

    @Test
    void testFluxSubscribe() {
        final Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void testFluxToListMono() {
        final Flux<Person> personFlux = personRepository.findAll();
        final Mono<List<Person>> personListMono = personFlux.collectList();
        personListMono.subscribe(list -> list.forEach(person -> System.out.println(person.toString())));
    }
}
