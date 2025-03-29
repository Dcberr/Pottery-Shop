package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
