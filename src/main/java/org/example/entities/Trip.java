package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Trip {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private String startPosition;
    private double price;
    @Enumerated(EnumType.STRING)
    private Category category;


    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Guide guide;
}
