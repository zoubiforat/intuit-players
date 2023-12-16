package com.intuit.demo.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "player")
public class Player {

    @Id
    private String playerId;

    private LocalDate dateOfBirth;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_of_birth_id", referencedColumnName = "id")
    private Place placeOfBirth;

    private LocalDate dateOfDeath;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_of_death_id", referencedColumnName = "id")
    private Place placeOfDeath;

    private String firstName;

    private String lastName;

    private String givenName;

    private Double weight;

    private Double height;

    @Enumerated(EnumType.STRING)
    private HandType handOfBat;

    @Enumerated(EnumType.STRING)
    private HandType handOfThrow;

    private LocalDate dateOfDebut;

    private LocalDate dateOfFinalGame;

    // TODO: Check what this means
    // Assumption: This is unique
    private String retroId;

    // TODO: Check what this means
    // Assumption: This is unique
    private String bbrefId;
}
