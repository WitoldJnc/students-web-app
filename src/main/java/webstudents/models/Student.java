package webstudents.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Setter
@Getter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private SchoolGroup group;

//    @JsonIgnore
//    @OneToMany(targetEntity = Mark.class,
//            mappedBy = "studentId", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY)
//    private Set<Mark> markSet;

    public Student(String firstName, String lastName, SchoolGroup group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group =group;
    }

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getGroup(){
        return group != null ? String.valueOf(group.getGroupNumber()) : "<none>";
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGroup(SchoolGroup group) {
        this.group = group;
    }
}
