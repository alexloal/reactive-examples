package guru.springframework.reactiveexamples.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author alejandrolopez
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
}
