package ro.apaic.registry.enitites;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String specializationName;

    @ManyToOne
    @JoinColumn(name = "study_domain_id")
    private StudyDomain studyDomain;
}
