package com.skt.netty.깡통;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

public class NettyHttpServer {

    private final int port;

    public NettyHttpServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        // 이벤트 루프 그룹 (Boss와 Worker 스레드 그룹)
        EventLoopGroup bossGroup = new NioEventLoopGroup();  // 클라이언트 연결을 처리
        EventLoopGroup workerGroup = new NioEventLoopGroup();  // 클라이언트의 요청을 처리

        try {
            // 서버 부트스트랩 설정
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)  // NIO 전송 채널
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 소켓 채널 초기화
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new HttpServerCodec());  // HTTP 요청/응답 코덱 추가
                        ch.pipeline().addLast(new HttpObjectAggregator(65536));  // HTTP 메시지 합치기
                        ch.pipeline().addLast(new SimpleHttpHandler());  // 직접 구현한 핸들러 추가
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // 대기열 크기 설정
                .childOption(ChannelOption.SO_KEEPALIVE, true);  // 연결 유지 옵션

            // 서버 바인드 및 시작
            ChannelFuture f = b.bind(port).sync();

            System.out.println("Server started on port: " + port);

            // 서버 채널이 닫힐 때까지 대기
            f.channel().closeFuture().sync();
        } finally {
            // 이벤트 루프 그룹 종료
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new NettyHttpServer(port).start();
    }
}

class SimpleHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        // HTTP 응답 생성
        String uri = request.uri();  // 요청 URI 가져오기
        FullHttpResponse response;

        if ("/hello".equals(uri)) {
            // /hello 경로 처리
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.content().writeBytes("김기훈 안녕!".getBytes());
        } else if ("/goodbye".equals(uri)) {
            // /goodbye 경로 처리
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.content().writeBytes("김기훈 잘가!".getBytes());
        } else {
            // 다른 경로 처리 (404 Not Found)
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
            response.content().writeBytes("경로 없음".getBytes());
        }

        // 헤더 설정
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

        // 응답 전송
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
