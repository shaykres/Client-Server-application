package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.NetworkSystemData;

import java.util.List;

public class NotificationMessage extends Message {
    private int NotificationType;
    private String postingUser;
    private String Content;
    public NotificationMessage(List<Object> argList) {
        super(argList);
        opCode=9;
        postingUser=(String) argList.get(0);
        Message m=(Message)argList.get(1);
        if (m instanceof PMMessage) {
            NotificationType=0;
            Content=((PMMessage) m).getContent();
        }
        else if(m instanceof PostMessage){
            NotificationType=1;
            Content=((PostMessage) m).getContent();
        }

    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Message process(int conID) {
        return null;
    }
}
