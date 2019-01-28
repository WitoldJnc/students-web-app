//package webstudents.models;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//@Entity
//@ToString
//@NoArgsConstructor
//@Setter
//@Getter
//@Table(name = "mark")
//public class Mark {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "discipline_id")
//    private Discipline diciplineId;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student studentId;
//    private Integer mark;
//}
