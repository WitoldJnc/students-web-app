package webstudents.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Setter
@Getter
@Table(name = "school_groups")
public class SchoolGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer groupNumber;

    @JsonIgnore
    @OneToMany(targetEntity = Student.class,
            mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "group_discipline",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "discipline_Id")})
    private Set<Discipline> disciplines = new HashSet<>();

    public SchoolGroup() {
    }

    public SchoolGroup(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

}
