package lol.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Champion {

    @Id @GeneratedValue
    @Column(name = "champion_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "position_id",joinColumns = @JoinColumn(name = "champion_id"),inverseJoinColumns = @JoinColumn(name = "position_id"))
    private final Set<Position> position = new HashSet<>();

    @Column(nullable = false)
    private String passive;

    @Column(nullable = false)
    private String passive_text;

    @Column(nullable = false)
    private String passive_img;

    @Column(nullable = false)
    private String q;

    @Column(nullable = false)
    private String q_text;

    @Column(nullable = false)
    private String q_img;

    @Column(nullable = false)
    private String w;

    @Column(nullable = false)
    private String w_text;

    @Column(nullable = false)
    private String w_img;

    @Column(nullable = false)
    private String e;

    @Column(nullable = false)
    private String e_text;

    @Column(nullable = false)
    private String e_img;

    @Column(nullable = false)
    private String r;

    @Column(nullable = false)
    private String r_text;

    @Column(nullable = false)
    private String r_img;

    @Column(nullable = false)
    private String img;

    @Column(nullable = true)
    private String tier;
}
