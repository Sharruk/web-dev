    package com.example.grocery.Repository;

    import com.example.grocery.Model.GroceryModel;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.bson.types.ObjectId;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    public interface GroceryRepository extends MongoRepository<GroceryModel, String> {

    }