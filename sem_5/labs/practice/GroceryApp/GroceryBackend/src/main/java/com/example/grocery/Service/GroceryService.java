package com.example.grocery.Service;

import com.example.grocery.Model.GroceryModel;
import com.example.grocery.Repository.GroceryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {

    private final GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    public List<GroceryModel> getAll() {

        return groceryRepository.findAll();
    }
}