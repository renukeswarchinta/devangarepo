package com.devangam.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devangam.model.security.Authority;

public interface RoleRepository extends JpaRepository<Authority, Long>{

}
