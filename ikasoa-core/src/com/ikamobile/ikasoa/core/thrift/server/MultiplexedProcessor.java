package com.ikamobile.ikasoa.core.thrift.server;

import java.util.Map;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thrift嵌套服务处理器
 * <p>
 * 作为扩展的服务处理器类,由thrift0.9.1引入,使用此类可以使一个服务启用多个Service.如果采用这种方式, 需要注意客户端将无法通过
 * <i>serverHost</i>和<i>serverPort</i> 定位具体的服务.所以建议不要将不相关的服务嵌套在一起.
 * <p>
 * 参数<i>processorMap</i>中的key需要提供给客户端,以便客户端定位具体的服务.
 * 
 * @author <a href="mailto:larry7696@gmail.com">Larry</a>
 * @version 0.1
 */
public class MultiplexedProcessor extends TMultiplexedProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MultiplexedProcessor.class);

	public MultiplexedProcessor(Map<String, TProcessor> processorMap) {
		if (processorMap != null) {
			for (String key : processorMap.keySet()) {
				registerProcessor(key, processorMap.get(key));
			}
		} else {
			LOG.warn("processorMap is null !");
		}
	}

}
