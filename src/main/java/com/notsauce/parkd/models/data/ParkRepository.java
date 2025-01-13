package com.notsauce.parkd.models.data;

import com.notsauce.parkd.models.Park;
import org.springframework.data.repository.CrudRepository;

public interface ParkRepository extends CrudRepository<Park, String> {
}
