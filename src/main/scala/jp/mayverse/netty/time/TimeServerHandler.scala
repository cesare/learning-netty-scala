package jp.mayverse.netty.time

import org.jboss.netty.channel.ChannelFuture
import org.jboss.netty.channel.ChannelFutureListener
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ChannelStateEvent
import org.jboss.netty.channel.ExceptionEvent
import org.jboss.netty.channel.SimpleChannelHandler

class TimeServerHandler extends SimpleChannelHandler {
  override def channelConnected(context: ChannelHandlerContext, e: ChannelStateEvent) = {
    val time = new UnixTime(System.currentTimeMillis().asInstanceOf[Int] / 1000)
    val future = e.getChannel().write(time)
    future.addListener(ChannelFutureListener.CLOSE)
  }
  
  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) = {
    e.getCause().printStackTrace()
    e.getChannel().close()
  }
}