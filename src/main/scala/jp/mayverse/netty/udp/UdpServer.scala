package jp.mayverse.netty.time

import java.net.InetSocketAddress
import java.util.concurrent.Executors

import org.jboss.netty.bootstrap.ConnectionlessBootstrap
import org.jboss.netty.channel.ChannelFactory
import org.jboss.netty.channel.ChannelPipeline
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.channel.Channels
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory

object UdpServer {
  def main(args: Array[String]) {
    val factory = new NioDatagramChannelFactory(Executors.newCachedThreadPool())
    val bootstrap = new ConnectionlessBootstrap(factory)
    bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
      def getPipeline(): ChannelPipeline = {
        Channels.pipeline(
          new UdpServerHandler()
        )
      }
    })

    bootstrap.setOption("child.tcpNoDelay", true)
    bootstrap.setOption("child.keepAlive", true)

    bootstrap.bind(new InetSocketAddress(9090))
  }
}
