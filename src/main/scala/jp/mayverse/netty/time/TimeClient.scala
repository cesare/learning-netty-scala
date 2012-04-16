package jp.mayverse.netty.time

import java.net.InetSocketAddress
import java.util.concurrent.Executors

import org.jboss.netty.bootstrap.ClientBootstrap
import org.jboss.netty.channel.ChannelFactory
import org.jboss.netty.channel.ChannelPipeline
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.channel.Channels
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory

object TimeClient {
  def main(args: Array[String]) {
    val host = args(0)
    val port = Integer.parseInt(args(1))
    
    val factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
    val bootstrap = new ClientBootstrap(factory)
    bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
      def getPipeline(): ChannelPipeline = {
        Channels.pipeline(
          new TimeDecoder(),
          new TimeClientHandler()
        )
      }
    })
    
    bootstrap.setOption("tcpNoDelay", true)
    bootstrap.setOption("keepAlive", true)
    bootstrap.connect(new InetSocketAddress(host, port))
  }
}