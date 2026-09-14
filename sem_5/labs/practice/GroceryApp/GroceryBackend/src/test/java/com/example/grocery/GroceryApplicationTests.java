package com.example.grocery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.example.grocery.Repository.GroceryRepository;
import com.example.grocery.Model.GroceryModel;
import java.util.List;

@SpringBootTest
class GroceryApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GroceryRepository groceryRepository;

	@Test
	void contextLoads() {
		System.out.println("TEST DB NAME: " + mongoTemplate.getDb().getName());
		System.out.println("TEST COLLECTIONS: " + mongoTemplate.getCollectionNames());
		List<GroceryModel> all = groceryRepository.findAll();
		System.out.println("TEST ALL PRODUCTS COUNT: " + all.size());
		for (GroceryModel item : all) {
			System.out.println("ITEM: " + item);
		}
	}

}
