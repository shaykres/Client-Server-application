package bgu.spl.net.impl.Messages;

import java.util.List;

public class LogStatMessage extends Message {
    private
    public LogStatMessage(List<String> arglist) {
        super(arglist);
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
