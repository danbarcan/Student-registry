package ro.apaic.registry.enitites;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "student_series")
public class StudentSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String studentSeriesName;

    @ManyToOne
    @JoinColumn(name = "study_year_id")
    private StudyYear studyYear;
}
