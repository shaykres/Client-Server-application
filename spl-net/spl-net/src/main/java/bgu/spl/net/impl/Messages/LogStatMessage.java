package bgu.spl.net.impl.Messages;

import java.util.List;

public class LogStatMessage extends Message {
    public LogStatMessage(List<String> arglist) {
        super(arglist);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
