package eu.mikroskeem.moarsms.http

import eu.mikroskeem.moarsms.Platform
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpContentCompressor
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.codec.http.HttpResponseEncoder

/**
 * @author Mark Vainomaa
 */
internal class HttpServerInitializer(private val platform: Platform) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) = ch.pipeline().run {
        addLast(HttpRequestDecoder())
        addLast(HttpObjectAggregator(1048576))
        addLast(HttpResponseEncoder())
        addLast(HttpContentCompressor())
        addLast(HttpServerHandler(platform))

        Unit
    }
}