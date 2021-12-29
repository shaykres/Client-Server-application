package bgu.spl.net.impl;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.impl.rci.Command;

public class BidiMessagingProtocolImpl implements BidiMessagingProtocol<Message> {

    boolean shouldTerminate;


    @Override
    public void start(int connectionId, Connections<Message> connections) {

    }

    @Override
    public void process(Message message) {

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
