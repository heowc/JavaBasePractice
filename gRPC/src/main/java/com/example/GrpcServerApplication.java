package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

public class GrpcServerApplication {

    private static final Logger logger = Logger.getLogger(GrpcServerApplication.class.getName());

    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new MessageSenderImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            GrpcServerApplication.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcServerApplication server = new GrpcServerApplication();
        server.start();
        server.blockUntilShutdown();
    }
}

class MessageSenderImpl extends MessageSenderGrpc.MessageSenderImplBase {

    private static final Logger logger = Logger.getLogger(MessageSenderImpl.class.getName());

    @Override
    public void send(MessageSenderProto.MessageRequest request, StreamObserver<MessageSenderProto.MessageResponse> responseObserver) {

        try {
            logger.fine(request.toString());

            MessageSenderProto.MessageResponse response = MessageSenderProto.MessageResponse.newBuilder()
                    .setStatus("success")
                    .setReason("ok")
                    .build();

            responseObserver.onNext(response);
        } catch (Exception ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }

    }
}