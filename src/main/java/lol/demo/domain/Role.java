package lol.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

}
