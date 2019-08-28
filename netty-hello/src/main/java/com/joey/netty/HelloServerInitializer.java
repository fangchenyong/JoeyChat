package com.joey.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
* Title:HelloServerInitializer
* Description:初始化器，channel注册后，会执行里面相应的
* Company: 
* @author 10136
* @date 2019年8月9日下午2:10:17
*/
public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// 通过SocketChannel去获得对应的管道
		ChannelPipeline pipeline  = channel.pipeline();
		
		//通过管道，添加handler
		//HttpServerCodec是由netty自己提供的助手类，可以理解为拦截器
		//当请求到服务器端，我们需要做解码，响应到客户端做编码
		pipeline.addLast("HttpServerCodec",new HttpServerCodec());
		
		//添加自定义的助手类，返回"Hello Netty~"
		pipeline.addLast("customHandler",new CustomHandler());
	}

}
