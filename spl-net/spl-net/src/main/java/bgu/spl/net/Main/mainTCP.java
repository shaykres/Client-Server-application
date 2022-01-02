package bgu.spl.net.Main;

import bgu.spl.net.impl.BidiMessagingProtocolImpl;
import bgu.spl.net.impl.MessageEncoderDecoderImpl;
import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.impl.NetworkSystemData;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Server;

import java.io.IOException;

public class mainTCP {
    public static void main(String[] args) throws IOException {
        try (BaseServer<Message> server = (BaseServer<Message>) Server.threadPerClient(7777, () -> new BidiMessagingProtocolImpl(), () -> new MessageEncoderDecoderImpl());) {
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
