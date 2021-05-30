package com.ecommerce.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userservice.Model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
