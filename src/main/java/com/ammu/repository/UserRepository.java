package com.ammu.repository;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ammu.model.RegistrationModel;

public interface UserRepository extends JpaRepository<RegistrationModel, Long> 
{
	Optional<RegistrationModel> findByEmail(String mail);
}
