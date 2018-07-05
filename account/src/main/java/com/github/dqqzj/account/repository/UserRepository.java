package com.github.dqqzj.account.repository;

import com.github.dqqzj.account.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 16:07
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User,String> {
}
