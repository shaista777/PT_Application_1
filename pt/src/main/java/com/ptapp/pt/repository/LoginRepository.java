package com.ptapp.pt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptapp.pt.model.Users;

@Repository
public interface LoginRepository extends JpaRepository<Users, String> {
	
	

}
