package com.ammu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ammu.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> 
{
	//DEFINE METHODS.
	UserModel findByEmail(String UserName);
	UserModel findByPassword(String password);
}
