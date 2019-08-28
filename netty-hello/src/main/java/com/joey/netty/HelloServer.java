package com.joey.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
* Title:HelloServer
* Description:实现客户端发送一个请求，服务器会返回Hello Netty。
* Company: 
* @author 10136
* @date 2019年8月9日上午11:33:34
*/
public class HelloServer {
	public static void main(String[] args) throws Exception {
		//主线程组，用于接收客户端的连接，但是不做任何处理
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//从线程组，老板线程组会把任务丢给手下线程组去做
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			//netty服务器的创建，ServerBootstrap是一个启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup)			//设置主从线程组
							.channel(NioServerSocketChannel.class)	//设置NIO双向通道
							.childHandler(new HelloServerInitializer());					//子处理器，用于处理workgroup
			
			//启动server，并设置8088为启动端口号，同时设置启动方式为同步
			ChannelFuture channelFuture= serverBootstrap.bind(8088).sync();
			
			//监听关闭的channel，设置为同步方式
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
