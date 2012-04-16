package jp.mayverse.netty.discard

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ExceptionEvent
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.SimpleChannelHandler

class DiscardServerHandler extends SimpleChannelHandler {
  override def messageReceived(context: ChannelHandlerContext, e: MessageEvent): Unit = {
    val buffer = e.getMessage().asInstanceOf[ChannelBuffer]
    while (buffer.readable()) {
      System.out.println(buffer.readByte())
      System.out.flush()
    }
  }
  
  override def exceptionCaught(context: ChannelHandlerContext, e: ExceptionEvent): Unit = {
    e.getCause().printStackTrace();

    val ch = e.getChannel();
    ch.close();
  }
}
