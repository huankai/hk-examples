/**
 * 
 */
package com.hk.amqp.example.domain;

/**
 * 发送通知的用户
 * 
 * @author kally
 * @date 2018年3月5日下午1:30:36
 */
public class NoticeUser {

	private String id;

	private String userName;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
