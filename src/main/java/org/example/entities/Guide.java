package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int yearsOfExperience;

    @OneToMany(mappedBy = "guide")
    private Set<Trip> trips;

}
