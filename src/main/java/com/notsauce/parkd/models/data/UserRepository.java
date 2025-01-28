package com.notsauce.parkd.models.data;

import com.notsauce.parkd.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Integer>{

User findByUsername(String username);
    
List<User> findByUsernameContainingIgnoreCase(String searchTerm);
   

}
