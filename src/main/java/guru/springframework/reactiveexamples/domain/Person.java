package guru.springframework.reactiveexamples.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author alejandrolopez
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
}
