package com.hussein.dao;

import com.hussein.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: UserRepository</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/12/31 8:18 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
