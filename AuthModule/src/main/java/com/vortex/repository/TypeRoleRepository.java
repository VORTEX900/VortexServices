package com.vortex.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.TypeUserRole;

@Repository
public interface TypeRoleRepository extends JpaRepository<TypeUserRole, Integer> {
}