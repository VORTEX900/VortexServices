package com.vortex.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vortex.model.TypeUserRole;

public interface TypeRoleRepository extends JpaRepository<TypeUserRole, Integer> {
}