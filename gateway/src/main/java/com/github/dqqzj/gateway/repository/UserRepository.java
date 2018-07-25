package com.github.dqqzj.gateway.repository;

import com.github.dqqzj.gateway.entity.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qzj.
 */
public interface UserRepository extends JpaRepository<UserDTO, String> {


}
