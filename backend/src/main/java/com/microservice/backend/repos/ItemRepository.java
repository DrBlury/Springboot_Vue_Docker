package com.microservice.backend.repos;

import com.microservice.backend.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
