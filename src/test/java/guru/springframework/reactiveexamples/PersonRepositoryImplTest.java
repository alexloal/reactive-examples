package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;

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
        // Verify the flux or mono
        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();
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
        StepVerifier.create(personFlux).expectNextCount(4).verifyComplete();
        personFlux.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void testFluxToListMono() {
        final Flux<Person> personFlux = personRepository.findAll();
        final Mono<List<Person>> personListMono = personFlux.collectList();
        personListMono.subscribe(list -> list.forEach(person -> System.out.println(person.toString())));
    }

    @Test
    void testFindPersonById() {
        final Integer id = 8;
        personRepository.findAll().filter(person -> Objects.equals(person.getId(), id))
                .next()
                .subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void testFindPersonByIdWithException() {
        final Integer id = 8;
        personRepository.findAll().filter(person -> Objects.equals(person.getId(), id)).single()
                .doOnError(throwable -> System.out.println("I went boom"))
                .onErrorReturn(Person.builder().id(id).build())
                .subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void findByIdAssignment() {
        final Person person = personRepository.findById(1).block();
        assert person != null && person.getId() == 1;
    }

    @Test
    void findByIdAssignmentEmpty() {
        final Mono<Person> person = personRepository.findById(10);
        StepVerifier.create(person).expectNextCount(0).verifyComplete();
        assert person.block() == null;
    }
}
