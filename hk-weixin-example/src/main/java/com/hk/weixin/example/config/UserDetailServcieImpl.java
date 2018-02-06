/**
 * 
 */
package com.hk.weixin.example.config;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailServiceImpl;
import com.hk.weixin.example.domain.User;
import com.hk.weixin.example.repository.UserRepository;

/**
 * @author kally
 * @date 2018年2月5日下午3:03:07
 */
public class UserDetailServcieImpl extends UserDetailServiceImpl {

	private UserRepository userRepository;

	@Override
	protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
		Optional<User> optional = userRepository.findUniqueByLoginName(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException("不存在的用户:" + username);
		}
		User user = optional.get();
		SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal(user.getId(), user.getUserName(),
				user.getPassWord(), user.getNickName(), user.getUserType(), user.getPhone(), user.getEmail(),
				user.getSex());
		return userPrincipal;
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
