/**
 * 
 */
package com.hk.amqp.example.domain;

import java.util.Date;
import java.util.List;

/**
 * 通知
 * 
 * @author kally
 * @date 2018年3月5日下午1:27:32
 */
public class Notice {

	private String id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发送者名称
	 */
	private String sendName;

	/**
	 * 发送时间
	 */
	private Date sendDate;

	/**
	 * 发送内容
	 */
	private String content;

	/**
	 * 通知的用户
	 */
	private List<NoticeUser> toUsers;

	/**
	 * 附件信息
	 */
	private List<NoticeAttachment> attachments;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the sendName
	 */
	public String getSendName() {
		return sendName;
	}

	/**
	 * @param sendName
	 *            the sendName to set
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate
	 *            the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the toUsers
	 */
	public List<NoticeUser> getToUsers() {
		return toUsers;
	}

	/**
	 * @param toUsers
	 *            the toUsers to set
	 */
	public void setToUsers(List<NoticeUser> toUsers) {
		this.toUsers = toUsers;
	}

	/**
	 * @return the attachments
	 */
	public List<NoticeAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<NoticeAttachment> attachments) {
		this.attachments = attachments;
	}

}
