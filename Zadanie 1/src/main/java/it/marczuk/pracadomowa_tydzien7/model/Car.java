package it.marczuk.pracadomowa_tydzien7.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    private Long carId;
    private String mark;
    private String model;
    private Color color;
    private int productionYear;

    public Car(String mark, String model, Color color, int productionYear) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }
}
