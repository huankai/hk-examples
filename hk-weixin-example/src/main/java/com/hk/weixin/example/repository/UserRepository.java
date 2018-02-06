package com.hk.weixin.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.hk.core.repository.StringRepository;
import com.hk.weixin.example.domain.User;

/**
 * 
 * @author huangkai
 * @date 2017年9月27日下午2:22:58
 */
public interface UserRepository extends StringRepository<User> {

	/**
	 * JPQL 语法查询，查询字段必须与属性字段相同，不是与数据库表字段
	 * 
	 * @param loginName
	 * @return
	 */
	@Query(value = "SELECT u FROM User u WHERE u.userName = ?1 OR u.phone = ?1 OR u.email = ?1")
	Optional<User> findUniqueByLoginName(String loginName);

}
