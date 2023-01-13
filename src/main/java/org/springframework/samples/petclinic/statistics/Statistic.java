package org.springframework.samples.petclinic.statistics;

import lombok.Getter;
import lombok.Setter;

import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "statistics")
public class Statistic extends BaseEntity {

    @NotNull
    private Integer totalPoints;

    @NotNull
    private Integer gamesPlayed;

    @NotNull
    private Integer gamesWon;

    @NotNull
    private Integer gamesLost;

}
