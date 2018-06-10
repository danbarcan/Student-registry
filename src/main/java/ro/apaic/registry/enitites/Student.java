package ro.apaic.registry.enitites;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    private String cnp;

    private LocalDate birthDate;

    private String fathersFirstName;

    private String mothersFirstName;

    private String cityOfBirth;

    private String placeOfBirth;

    private String countryOfBirth;

    private String citizenship;

    private String registryNo;

    private String decisionNo;

    private LocalDate decisionDate;

    @ManyToOne
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;
}
