package com.notsauce.parkd.models.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notsauce.parkd.models.ParkdUser;

@Repository
public interface ParkdUserRepository extends JpaRepository<ParkdUser, Integer> {
    List<ParkdUser> findByUsernameContainingIgnoreCase(String searchTerm);
    List<ParkdUser> findByEmailContainingIgnoreCase(String searchTerm);
}