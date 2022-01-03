package bgu.spl.net.impl.Messages;

import java.util.List;

public class AckMessage extends Message {

    public AckMessage(List<Object> args) {
        super(args);
        opCode=10;
        System.out.println("i am in ack");
    }

    @Override
    public byte[] encode() {
        byte[] opcode=shortToBytes(opCode);
        byte[] body=((Message)argList.get(0)).encode();
        byte[] message=new byte[3+body.length];
        message[0]=opcode[0];
        message[1]=opcode[1];
        for(int i=0;i<body.length;i++)
            message[i+2]=body[i];
        message[2+body.length]=';';
        return message;
    }

    @Override
    public Message process(int conID) {
        return null;
    }
}
