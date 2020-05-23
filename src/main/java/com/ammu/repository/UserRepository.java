package com.ammu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ammu.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel , Integer> 
{
	//DEFINE METHODS.
	
	Optional<UserModel> findByEmail(String email);
	Optional<UserModel> findByPassword(String password);
}
