package com.example.cardapio.controllers;


import com.example.cardapio.entities.Food;
import com.example.cardapio.repositories.FoodRepository;
import com.example.cardapio.response.FoodResponseDTO;
import com.example.cardapio.request.FoodRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {FoodController.class})
public class FoodControllerTest {

    @Autowired
    FoodController foodController;

    @MockBean
    private FoodRepository foodRepository;

    @Test
    @DisplayName("Retorna 204 No Content quando não há alimentos na lista")
    void testGetListEmptyFood() {

        // Configuração do mock
        when(foodRepository.findAll()).thenReturn(Collections.emptyList());

        // Executando o metodo testado
        ResponseEntity<List<FoodResponseDTO>> responseEntity = foodController.getAllFoods();

        // Verificações
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Retorna 200 OK quando há alimentos na lista")
    void testGetListFood() {

        // Configuração do mock
        Food food1 = new Food(1L, "Food 1", "image1.jpg", 10);
        Food food2 = new Food(2L, "Food 2", "image2.jpg", 20);
        Food food3 = new Food(3L, "Food 3", "image3.jpg", 30);

        List<Food> foodList = List.of(food1, food2, food3);

        when(foodRepository.findAll()).thenReturn(foodList);

        // Transformando os objetos de Food para FoodResponseDTO
        List<FoodResponseDTO> expectedResponseList = foodList.stream().map(FoodResponseDTO::new).toList();

        // Executando o método testado
        ResponseEntity<List<FoodResponseDTO>> responseEntity = foodController.getAllFoods();

        // Verificações
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        assertEquals(expectedResponseList, responseEntity.getBody());
    }

    @Test
    @DisplayName("Retorna 201 Created ao criar um novo alimento")
    void testNewFood() {

        // Dados de entrada
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO("Food 1", "image1.jpg", 10);

        // Configuração do Mock
        // Simulando o save do alimento
        when(foodRepository.save(any(Food.class))).thenReturn(new Food(1L, "Food 1", "image1.jpg", 10));

        // Executando o método testado
        ResponseEntity<String> responseEntity = foodController.newFood(foodRequestDTO);

        // Verificações
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Food 1 criado com sucesso!", responseEntity.getBody());

    }

    @Test
    @DisplayName("Retorna 200 OK ao excluir um alimento existente")
    void testDeleteExistingFood() {

        // Dados de entrada
        Long foodId = 1L;

        // Mock do repositório para simular a busca do alimento
        Food food = new Food(foodId,"Food 1", "image1.jpg", 10);
        when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));

        // Executando o método testado
        ResponseEntity<String> responseEntity = foodController.deleteFood(foodId);

        // Verificações
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Alimento Food 1 excluído com sucesso!", responseEntity.getBody());
    }

    @Test
    @DisplayName("Retorna 404 Not Found ao excluir um alimento inexistente")
    void testDeleteNotExistingFood() {

        // Dados de entrada
        Long foodId = 1L;

        // Mock do repositório para simular a busca do alimento inexistente
        when(foodRepository.findById(foodId)).thenReturn(Optional.empty());

        // Executando o método testado
        ResponseEntity<String> responseEntity = foodController.deleteFood(foodId);

        // Verificações
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Esse alimento não existe.", responseEntity.getBody());
    }
}
