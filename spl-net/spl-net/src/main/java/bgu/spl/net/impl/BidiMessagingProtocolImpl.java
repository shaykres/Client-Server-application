package bgu.spl.net.impl;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.Messages.AckMessage;
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
        System.out.println("message ack got back");
        connections.send(connectionId,tosend);
        if(tosend instanceof AckMessage){
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            AckMessage ack=(AckMessage)tosend;
            int opcode=((Integer)((Message)tosend.getArgList().get(0)).getopCode());
           // System.out.println("!!!!!!!!!!!!"+opcode);
            if(opcode==3) {
                System.out.println("disconnect client "+connectionId);
                connections.disconnect(connectionId);
            }
        }
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
