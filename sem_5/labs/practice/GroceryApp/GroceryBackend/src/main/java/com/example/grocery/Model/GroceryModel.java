package com.example.grocery.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class GroceryModel {
    @Id
    private String id;
    private String name;
    private String category;
    private Double price;
    private Integer stock;
}