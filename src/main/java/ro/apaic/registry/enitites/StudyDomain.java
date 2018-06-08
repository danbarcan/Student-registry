package ro.apaic.registry.enitites;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "study_domains")
public class StudyDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String studyDomainName;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
