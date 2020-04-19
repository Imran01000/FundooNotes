package com.ammu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ammu.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> 
{
	
}
