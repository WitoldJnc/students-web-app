package webstudents.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

@NoArgsConstructor
@Setter
@Getter
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline disciplineId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;
    private Integer mark;

    public Mark(Discipline diciplineId, Student studentId, Integer mark) {
        this.disciplineId = diciplineId;
        this.studentId = studentId;
        this.mark = mark;
    }

    public Mark(Student studentId, Integer mark) {
        this.studentId = studentId;
        this.mark = mark;
    }

    public Mark(Integer mark) {
        this.mark = mark;
    }
}
