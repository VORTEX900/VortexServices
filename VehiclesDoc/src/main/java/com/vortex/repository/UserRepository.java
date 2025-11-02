package com.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

	User findByAlias(String alias);
	
}
