package webstudents.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "disciplines")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String disciplineName;

    @ManyToMany(mappedBy = "disciplines", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<SchoolGroup> schoolGroups;

    @JsonIgnore
    @OneToMany(targetEntity = Mark.class,
            mappedBy = "disciplineId", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Mark> markSet;

    public Discipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

}
