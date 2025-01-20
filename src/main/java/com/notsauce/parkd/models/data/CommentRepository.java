package com.notsauce.parkd.models.data;

import com.notsauce.parkd.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
