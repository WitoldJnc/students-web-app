package webstudents.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roles")
    private String userRole;

    @JsonIgnore
    @OneToMany(targetEntity = User.class,
    mappedBy = "roles",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<User> users;

    @Override
    public String getAuthority() {
        return userRole;
    }


}