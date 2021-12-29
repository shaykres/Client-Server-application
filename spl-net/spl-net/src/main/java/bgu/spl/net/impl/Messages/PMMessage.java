package bgu.spl.net.impl.Messages;

import java.util.List;

public class PMMessage extends Message{
    public PMMessage(List<String> arglist) {
        super(arglist);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
