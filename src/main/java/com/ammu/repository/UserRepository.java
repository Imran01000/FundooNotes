package com.ammu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ammu.model.LoginModel;
import com.ammu.model.RegistrationModel;

public interface UserRepository extends JpaRepository<LoginModel, Long> 
{
	
}
