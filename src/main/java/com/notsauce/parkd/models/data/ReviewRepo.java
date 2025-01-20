package com.notsauce.parkd.models.data;

import com.notsauce.parkd.models.Review;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review, Integer> {
}
