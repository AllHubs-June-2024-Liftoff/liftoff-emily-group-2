package com.notsauce.parkd.models.data;

import com.notsauce.parkd.models.Park;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends CrudRepository<Park, String> {

    Park findByparkCode(String parkCode);

}
