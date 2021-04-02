package com.ptapp.pt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptapp.pt.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
	List<Users> findByUserName(String userName);
	List<Users> findByUserNameAndPassword(String userName,String password);

}
