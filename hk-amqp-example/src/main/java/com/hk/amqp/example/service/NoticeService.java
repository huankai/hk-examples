/**
 * 
 */
package com.hk.amqp.example.service;

import com.hk.amqp.example.SenderService;
import com.hk.amqp.example.domain.Notice;
import com.hk.commons.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kally
 * @date 2018年3月5日下午2:47:23
 */
@Service
public class NoticeService {

	@Autowired
	private SenderService senderService;

	/**
	 * 保存
	 * 
	 * @param notice
	 */
	public void save(Notice notice) {
		String data = JsonUtils.toJSONString(notice);
		System.out.println("Sender:" + data);
		senderService.send(data);
	}
}
