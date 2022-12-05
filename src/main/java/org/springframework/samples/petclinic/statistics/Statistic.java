package org.springframework.samples.petclinic.statistics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.player.Player;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "statistic")
public class Statistic extends NamedEntity {

    @Column(name = "totalPoints")
    @NotNull
    private Integer totalPoints;

    @Column( name = "gamesPlayed" )
    @NotNull
    private Integer gamesPlayed;

    @Column( name = "gamesWon")
    @NotNull
    private Integer gamesWon;

    @Column( name = "gamesLost" )
    @NotNull
    private Integer gamesLost;

    @OneToOne( cascade = CascadeType.ALL )
    @NotNull
    @JoinColumn( name = "playerid", referencedColumnName = "id")
    private Player player;

}
