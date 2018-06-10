package ro.apaic.registry.enitites;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String subjectName;

    private int credits;

    private int cursHours;

    private int slpHours;

    @ManyToOne
    @JoinColumn(name = "study_year_id")
    private StudyYear studyYear;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}