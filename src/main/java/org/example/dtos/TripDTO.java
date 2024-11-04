package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.Category;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripDTO {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private String startPosition;
    private String name;
    private double price;
    private Category category;

    private GuideDTO guide;

}
