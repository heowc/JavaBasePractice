package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClientApplication {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        MessageSenderGrpc.MessageSenderBlockingStub messageSenderBlockingStub = MessageSenderGrpc.newBlockingStub(channel);

        MessageSenderProto.MessageRequest messageRequest = MessageSenderProto.MessageRequest.newBuilder()
                .setFrom("wonchul")
                .setTo("naeun")
                .setContents("hello!")
                .build();

        MessageSenderProto.MessageResponse messageResponse = messageSenderBlockingStub.send(messageRequest);
        System.out.println(messageResponse.toString());
    }
}
