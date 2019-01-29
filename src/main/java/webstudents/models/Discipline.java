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
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String disciplineName;

    @ManyToMany(mappedBy = "disciplines")
    private Set<SchoolGroup> schoolGroups;

    @JsonIgnore
    @OneToMany(targetEntity = Mark.class,
            mappedBy = "diciplineId", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Mark> markSet;


    public Discipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
