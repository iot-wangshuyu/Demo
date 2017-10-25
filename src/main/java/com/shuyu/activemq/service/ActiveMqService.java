package com.shuyu.activemq.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface ActiveMqService {
	 public final ExecutorService pushExecutor = Executors.newFixedThreadPool(10);  
     
	    public void push(String queueName,final Object info);  

}
