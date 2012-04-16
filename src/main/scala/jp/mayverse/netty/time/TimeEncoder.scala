package jp.mayverse.netty.time

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.Channels
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.SimpleChannelHandler

import org.jboss.netty.buffer.ChannelBuffers._

class TimeEncoder extends SimpleChannelHandler {
  override def writeRequested(context: ChannelHandlerContext, e: MessageEvent) = {
    val time = e.getMessage().asInstanceOf[UnixTime]
    val buf = buffer(4)
    buf.writeInt(time.value)
    
    Channels.write(context, e.getFuture(), buf)
  }
}
