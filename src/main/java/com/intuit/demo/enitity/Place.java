package com.intuit.demo.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue
    private long id;

    private String city;

    private String state;

    @Column(nullable = false)
    private String country;
}
