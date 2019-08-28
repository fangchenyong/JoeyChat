package com.joey.websocket;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
* Title:ChatHandler
* Description:处理消息的handler，
* TextWebSocketFrame：在netty中，适用于为websocket专门处理文本的对象，frame是消息的载体
* Company: 
* @author 10136
* @date 2019年8月12日上午10:25:57
*/
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	private static ChannelGroup clients = 
			new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) 
			throws Exception {
		//获取客户端传输过来的消息
		String content = msg.text();
		System.out.println("接收到的数据："+content);
//		for (Channel channel:clients) {
//			channel.writeAndFlush(
//					new TextWebSocketFrame("服务器在：["+LocalDateTime.now()
//					+"],收到消息：["+content+"]"));
//		}
		//下面的方法和上面的for循环一致
		clients.writeAndFlush(
				new TextWebSocketFrame("服务器在：["+LocalDateTime.now()
					+"],收到消息：["+content+"]"));
	}

	/**
	 * 当客户端连接服务端之后（打开连接）
	 * 获取客户端的channel，并且放到ChannelGroup中去进行管理
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		clients.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
		clients.remove(ctx.channel());
		System.out.println("客户端断开，长ID："+ctx.channel().id().asLongText());
		System.out.println("客户端断开，短ID："+ctx.channel().id().asShortText());
	}
	
	

}
