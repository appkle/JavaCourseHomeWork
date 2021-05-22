package server.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        if (fullRequest.headers().get("token") == null || fullRequest.headers().get("token") == ""){
            return true;
        }
        else{
            return false;
        }
    }
}
