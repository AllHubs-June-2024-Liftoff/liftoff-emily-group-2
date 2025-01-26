package com.notsauce.parkd.models.data;

import com.notsauce.parkd.models.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository <Blog, Integer> {
}
