package bgu.spl.net.Main;

import bgu.spl.net.impl.BidiMessagingProtocolImpl;
import bgu.spl.net.impl.MessageEncoderDecoderImpl;
import bgu.spl.net.impl.NetworkSystemData;
import bgu.spl.net.srv.Server;

import java.io.IOException;

public class mainReactor {
    public static void main(String[] args) throws IOException {
        NetworkSystemData data = NetworkSystemData.getInstance();
        Server.reactor(3, 7777,
                () -> new BidiMessagingProtocolImpl(),
                () -> new MessageEncoderDecoderImpl()).serve();
//        Server.reactor(Integer.parseInt(args[1]), Integer.parseInt(args[0]),
//                () -> new BidiMessagingProtocolImpl(),
//                () -> new MessageEncoderDecoderImpl()).serve();
    }
}
