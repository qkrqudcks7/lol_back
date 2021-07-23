package lol.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

    @Id @GeneratedValue
    @Column(name = "position_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EPosition name;
}
