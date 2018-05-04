package com.hk.weixin.example.domain;

import com.hk.commons.annotations.EnumDisplay;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户基本信息
 * 
 * @author huangkai
 * @date 2017年9月22日下午3:05:36
 */
@Entity
@Table(name = "sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends ModelHolder.UserBase {

	/**
	 *
	 */
	private static final long serialVersionUID = -2561707826944989624L;

	public enum UserStatus {

		/**
		 * 禁用
		 */
		@EnumDisplay(value = "禁用", order = 0)
		DISABLE,

		/**
		 * 启用
		 */
		@EnumDisplay(value = "启用", order = 1)
		ENABLE,

		/**
		 * 锁定
		 */
		@EnumDisplay(value = "锁定", order = 9)
		LOCKED
	}

}
