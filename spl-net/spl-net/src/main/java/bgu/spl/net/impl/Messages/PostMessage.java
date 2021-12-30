package bgu.spl.net.impl.Messages;

import java.util.List;

public class PostMessage extends Message {
    public PostMessage(List<String> arglist) {
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
