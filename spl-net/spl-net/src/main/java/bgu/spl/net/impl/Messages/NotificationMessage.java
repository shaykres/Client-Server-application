package bgu.spl.net.impl.Messages;

import java.util.List;

public class NotificationMessage extends Message {
    public NotificationMessage(List<Object> argList) {
        super(argList);
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
