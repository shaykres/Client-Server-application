package bgu.spl.net.impl.Messages;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class FollowMessage extends Message {
    private boolean follow;
    private String userName;

    public FollowMessage(List<Object> arglist) {
        super(arglist);
        opCode=4;
        if(arglist.get(0).equals("0"))
            follow=true;
        else
            follow=false;
        userName=(String)arglist.get(1);
    }

    @Override
    public byte[] encode() {
        byte[] opcode=shortToBytes(opCode);
        byte[] body=userName.getBytes(StandardCharsets.UTF_8);
        byte[] message=new byte[2+body.length];
        message[0]=opcode[0];
        message[1]=opcode[1];
        for(int i=0;i<body.length;i++)
            message[i+2]=body[i];
        return message;
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.FollowClient(conID,follow,userName);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }
}
