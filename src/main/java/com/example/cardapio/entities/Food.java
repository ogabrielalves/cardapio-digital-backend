package com.example.cardapio.entities;

import com.example.cardapio.request.FoodRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "foods")
@Entity(name = "foods")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "O title não deve ser vazio!")
    private String title;

    private String image;

    @NotNull
    @PositiveOrZero(message = "O valor deve ser maior ou igual a zero.")
    private Integer price;

    public Food(FoodRequestDTO newFood) {
        this.title = newFood.title();
        this.image = newFood.image();
        this.price = newFood.price();
    }

}
