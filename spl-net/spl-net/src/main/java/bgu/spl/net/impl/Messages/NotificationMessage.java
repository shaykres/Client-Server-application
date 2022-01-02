package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.NetworkSystemData;

import java.nio.charset.StandardCharsets;
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
        byte[]opcode=shortToBytes(opCode);
        byte notifcaition= (byte) NotificationType;
        byte[] postUser=postingUser.getBytes(StandardCharsets.UTF_8);
        byte[] content=Content.getBytes(StandardCharsets.UTF_8);
        byte[] message=new byte[5+postUser.length+content.length];
        message[0]=opcode[0];
        message[1]=opcode[1];
        message[2]=notifcaition;
        for (int i=0;i<postUser.length;i++){
            message[i+2]=postUser[i];
        }
        message[3+postUser.length]=0;
        for (int i=0;i<content.length;i++){
            message[i+4+postUser.length]=content[i];
        }
        message[4+postUser.length+content.length]=0;
        return message;
    }

    @Override
    public Message process(int conID) {
        return null;
    }
}
