package jp.mayverse.netty.time

import java.nio.charset.Charset
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ExceptionEvent
import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.MessageEvent

class UdpServerHandler extends SimpleChannelHandler {
  override def messageReceived(context: ChannelHandlerContext, e: MessageEvent): Unit = {
    val buffer = e.getMessage().asInstanceOf[ChannelBuffer]
    System.out.println(buffer.toString(Charset.forName("UTF-8")))
  }
  
  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) = {
    e.getCause().printStackTrace()
    e.getChannel().close()
  }
}