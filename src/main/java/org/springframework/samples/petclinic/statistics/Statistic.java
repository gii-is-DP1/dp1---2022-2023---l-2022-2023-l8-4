package org.springframework.samples.petclinic.statistics;

import lombok.Getter;
import lombok.Setter;

import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "statistic")
public class Statistic extends BaseEntity {

    @Column(name = "total_points")
    @NotNull
    private Integer totalPoints;

    @Column( name = "games_played" )
    @NotNull
    private Integer gamesPlayed;

    @Column( name = "games_won")
    @NotNull
    private Integer gamesWon;

    @Column( name = "games_lost" )
    @NotNull
    private Integer gamesLost;

}
