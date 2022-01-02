package bgu.spl.net.impl.Messages;

import java.util.List;

public class ErrorMessage extends Message {

    public ErrorMessage(List<Object> argList) {
        super(argList);
        opCode=11;

    }

    @Override
    public byte[] encode() {
        byte[] opcode=shortToBytes(opCode);
        byte[] subject=shortToBytes(((Message)argList.get(0)).opCode);
        byte[] message=new byte[4];
        message[0]=opcode[0];
        message[1]=opcode[1];
        message[2]=subject[0];
        message[3]=subject[1];

        return message;
    }

    @Override
    public Message process(int conID) {
        return null;
    }

}
