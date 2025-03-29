package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
