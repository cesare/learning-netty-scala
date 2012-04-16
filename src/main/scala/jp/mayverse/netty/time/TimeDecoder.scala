package jp.mayverse.netty.time

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.frame.FrameDecoder

class TimeDecoder extends FrameDecoder {
  override def decode(context: ChannelHandlerContext , channel: Channel, buffer: ChannelBuffer): Object = {
    if (buffer.readableBytes() < 4) {
      return null;
    }
    
    new UnixTime(buffer.readInt());
  }
}
