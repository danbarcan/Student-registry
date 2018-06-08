package ro.apaic.registry.enitites;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "student_groups")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String studentGroupName;

    @ManyToOne
    @JoinColumn(name = "student_series_id")
    private StudentSeries studentSeries;
}
