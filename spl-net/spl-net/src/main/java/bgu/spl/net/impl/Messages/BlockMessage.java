package bgu.spl.net.impl.Messages;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class BlockMessage extends Message{
    private String UserNameToBlock;
    public BlockMessage(List<Object> argList) {
        super(argList);
        UserNameToBlock=(String)argList.get(0);

    }

    @Override
    public byte[] encode() {
        byte[] opcode=shortToBytes(opCode);
        byte[] body=UserNameToBlock.getBytes(StandardCharsets.UTF_8);
        byte[] message=new byte[2+body.length+1];
        message[0]=opcode[0];
        message[1]=opcode[1];
        for(int i=0;i<body.length;i++)
            message[2+i]=body[i];
        message[2+body.length]=0;
        return message;
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.BlockUser(conID,UserNameToBlock);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }
}
