package com.microservice.backend.repos;

import com.microservice.backend.entity.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {

}
