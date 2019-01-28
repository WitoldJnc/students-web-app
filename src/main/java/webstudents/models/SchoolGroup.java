package webstudents.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @ElementCollection(targetClass = Student.class, fetch = FetchType.EAGER)
    @JsonIgnore
    @OneToMany(targetEntity=Student.class,
            mappedBy="group",cascade=CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Student> students;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "group_discipline",
//    joinColumns = {@JoinColumn(name = "group_id")},
//    inverseJoinColumns = {@JoinColumn(name = "discipline_Id")})
//    private Set<Discipline> disciplines;


    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public SchoolGroup() {
    }

    public SchoolGroup(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }
}
