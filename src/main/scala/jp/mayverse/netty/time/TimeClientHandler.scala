package jp.mayverse.netty.time

import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.ExceptionEvent
import org.jboss.netty.channel.SimpleChannelHandler

class TimeClientHandler extends SimpleChannelHandler {
  override def messageReceived(context: ChannelHandlerContext, e: MessageEvent) = {
    val time = e.getMessage().asInstanceOf[UnixTime]
    System.out.println(time);
    e.getChannel().close();
  }
  
  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) = {
    e.getCause().printStackTrace();
    e.getChannel().close();
  }
}