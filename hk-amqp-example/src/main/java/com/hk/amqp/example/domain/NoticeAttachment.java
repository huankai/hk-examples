package com.hk.amqp.example.domain;

/**
 * 通知附件信息
 * 
 * @author kevin
 * @date 2018年3月5日下午1:28:02
 */
public class NoticeAttachment {

	/**
	 * 附件名称
	 */
	private String attachmentName;

	/**
	 * 附件路径
	 */
	private String path;

	/**
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * @param attachmentName
	 *            the attachmentName to set
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
