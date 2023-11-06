package com.example.inventory.repository;

import com.example.inventory.model.Item;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepository extends MongoRepository <Item,String> {
    List<Item> findByIdIn(List<String> ids);
}
