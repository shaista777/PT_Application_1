package com.ptapp.pt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptapp.pt.model.Documents;
import com.ptapp.pt.model.Users;

@Repository
public interface DocsRepository extends JpaRepository<Documents, String> {
	List<Users> findByUserName(String userName);

}
