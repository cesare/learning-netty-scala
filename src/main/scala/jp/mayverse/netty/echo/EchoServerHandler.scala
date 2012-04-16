package jp.mayverse.netty.echo

import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.SimpleChannelHandler

class EchoServerHandler extends SimpleChannelHandler {
  override def messageReceived(context: ChannelHandlerContext, e: MessageEvent) = {
    val ch = e.getChannel();
    ch.write(e.getMessage());
  }
}
