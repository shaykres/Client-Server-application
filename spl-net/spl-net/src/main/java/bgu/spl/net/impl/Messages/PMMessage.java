package bgu.spl.net.impl.Messages;

import java.util.List;

public class PMMessage extends Message{
    public PMMessage(List<Object> arglist) {
        super(arglist);
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
        return null;
    }
}
