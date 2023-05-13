package com.example.cardapio.controllers;

import com.example.cardapio.entities.Food;
import com.example.cardapio.request.FoodRequestDTO;
import com.example.cardapio.response.FoodResponseDTO;
import com.example.cardapio.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getAllFoods() {
        List<FoodResponseDTO> foodList = foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList.isEmpty() ? status(HttpStatus.NO_CONTENT).build() : status(HttpStatus.OK).body(foodList);
    }

    @PostMapping
    public ResponseEntity<String> newFood(@RequestBody FoodRequestDTO newFood) {
        Food food = new Food(newFood);
        foodRepository.save(food);
        return status(HttpStatus.CREATED).body(food.getTitle() + " criado com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        Optional<Food> foodOptional = foodRepository.findById(id);
        
        if (foodOptional.isPresent()) {
            Food food = foodOptional.get();
            foodRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Alimento " + food.getTitle() + " excluído com sucesso!");
        }
        return status(HttpStatus.NOT_FOUND).body("Esse alimento não existe.");
    }

}
