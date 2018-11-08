package com.hk.weixin.example.repository.jpa;

import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.weixin.example.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * 
 * @author: kevin
 * @date: 2017年9月27日下午2:22:58
 */
public interface UserRepository extends StringIdJpaRepository<User> {

	/**
	 * JPQL 语法查询，查询字段必须与属性字段相同，不是与数据库表字段
	 * 
	 * @param loginName
	 * @return
	 */
	@Query(value = "SELECT u FROM User u WHERE u.userName = ?1 OR u.phone = ?1 OR u.email = ?1")
	Optional<User> findUniqueByLoginName(String loginName);

}
