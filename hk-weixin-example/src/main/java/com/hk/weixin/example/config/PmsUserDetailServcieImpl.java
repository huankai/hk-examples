/**
 * 
 */
package com.hk.weixin.example.config;

import org.hk.pms.api.FcadeUserService;
import org.hk.pms.domain.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailServiceImpl;

/**
 * @author kally
 * @date 2018年2月5日下午3:03:07
 */
public class PmsUserDetailServcieImpl extends UserDetailServiceImpl {

	private FcadeUserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.authentication.security.UserDetailServiceImpl#
	 * loadUserByLoginUsername(java.lang.String)
	 */
	@Override
	protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
		User user = userService.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("用户不存在:" + username);
		}
		SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal(user.getUserId(), user.getUserName(), null,
				user.getNickName(), user.getUserType(), user.getPhone(), user.getEmail(), user.getSex());
		userPrincipal.setRoles(user.getRoles());
		userPrincipal.setPermissions(user.getPermissions());
		return userPrincipal;
	}

}
