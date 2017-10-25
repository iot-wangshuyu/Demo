package com.shuyu.activemq.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.shuyu.activemq.service.ActiveMqService;
import com.shuyu.utils.GsonUtil;

@Service
public class TopicServiceImpl implements ActiveMqService {

	// 通过@Qualifier修饰符来注入对应的bean
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	@Override
	public void push(final String queueName, final Object info) {
		pushExecutor.execute(new Runnable() {
			@Override
			public void run() {
				jmsTemplate.send(queueName, new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						return session.createTextMessage(GsonUtil.GsonString(info));
					}
				});
			}
		});

	}

}
