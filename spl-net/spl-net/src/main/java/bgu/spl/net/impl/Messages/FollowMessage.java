package bgu.spl.net.impl.Messages;

import java.util.List;

public class FollowMessage extends Message {
    public FollowMessage(List<String> arglist) {
        super(arglist);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
