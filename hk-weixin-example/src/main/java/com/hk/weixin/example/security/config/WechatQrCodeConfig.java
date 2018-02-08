/**
 * 
 */
package com.hk.weixin.example.security.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.hk.commons.util.StringUtils;

/**
 * 微信二维码扫码参数
 * 
 * @author kally
 * @date 2018年2月8日下午3:50:15
 */
@Configuration
@ConfigurationProperties(prefix = "wx.qrcode")
public class WechatQrCodeConfig {

	/**
	 * 二维码扫码回调
	 */
	@NotNull
	private String callbackUrl;

	private String callHost;

	/**
	 * @return the callbackUrl
	 */
	public String getCallbackUrl() {
		return callbackUrl;
	}

	/**
	 * @param callbackUrl
	 *            the callbackUrl to set
	 */
	public void setCallbackUrl(String callbackUrl) {
		if (!StringUtils.startsWith(callbackUrl, "/")) {
			callbackUrl = "/" + callbackUrl;
		}
		this.callbackUrl = callbackUrl;
	}

	/**
	 * @return the callHost
	 */
	public String getCallHost() {
		return callHost;
	}

	/**
	 * @param callHost
	 *            the callHost to set
	 */
	public void setCallHost(String callHost) {
		this.callHost = callHost;
	}

}
