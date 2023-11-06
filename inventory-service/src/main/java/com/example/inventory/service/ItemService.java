package com.example.inventory.service;

import com.example.inventory.dto.ItemRequest;
import com.example.inventory.dto.ItemResponse;
import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createItem(ItemRequest itemRequest){
        UUID id = UUID.randomUUID();
        Item item = new  Item(id.toString(),
                itemRequest.getName(),
                itemRequest.getDescription(),
                itemRequest.getQuantity(),
                itemRequest.getPrice(),
                itemRequest.getAddedBy(),
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));

        itemRepository.save(item);
        System.out.println("item "+item.getId()+"is saved");
    }

    public List<ItemResponse> getAllItems(){
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::mapToItemResponse).toList();
    }

    public ItemResponse getItemById(String id){
        Item item = itemRepository.findById(id).orElse(null);
        if(item!=null){
            return mapToItemResponse(item);
        }else {
            return null;
        }

    }

    public ItemResponse mapToItemResponse(Item item){
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getQuantity(),
                item.getPrice(),
                item.getAddedBy(),
                item.getLastUpdated()
        );
    }

    public Item updateItem(ItemResponse updatedItem) {
        Item item = itemRepository.findById(updatedItem.getId()).orElse(null);
        if (item != null) {
            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
            item.setQuantity(updatedItem.getQuantity());
            item.setPrice(updatedItem.getPrice());
            item.setAddedBy(updatedItem.getAddedBy());
            item.setLastUpdated( new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));
            return itemRepository.save(item);
        }
        return null;
    }

    public boolean deleteItem(String id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            itemRepository.delete(item);
            return true;
        }
        return false;
    }

    public boolean isInStock(List<String> id){
         List<Item> items = itemRepository.findByIdIn(id);
        boolean isQuantityEnough = true;


         for(Item i:items){
             if (i.getQuantity() <= 0) {
                 isQuantityEnough = false;
                 break;
             }
         }

         return isQuantityEnough;
    }
}
