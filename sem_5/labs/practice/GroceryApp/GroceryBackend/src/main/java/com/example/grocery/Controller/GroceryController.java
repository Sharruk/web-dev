package com.example.grocery.Controller;

import com.example.grocery.Model.GroceryModel;
import com.example.grocery.Service.GroceryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GroceryController {

    private final GroceryService groceryService;

    // Use constructor injection (no @Autowired needed in newer Spring versions)
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @GetMapping("/products")
    public List<GroceryModel> getAllItems() {
        List<GroceryModel> items = groceryService.getAll();
        System.out.println("DEBUG: Number of items found in DB = " + items.size());
        return items;
    }
}