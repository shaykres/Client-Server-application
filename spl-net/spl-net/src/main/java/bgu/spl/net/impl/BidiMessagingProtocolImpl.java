package bgu.spl.net.impl;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.impl.rci.Command;

public class BidiMessagingProtocolImpl implements BidiMessagingProtocol<Message> {

    boolean shouldTerminate;
    private int connectionId;
    private Connections<Message> connections;


    @Override
    public void start(int connectionId, Connections<Message> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
        shouldTerminate=false;
    }

    @Override
    public void process(Message message) {
        Message tosend=message.process(connectionId);
        connections.send(connectionId,tosend);
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
