package bgu.spl.net.impl.Messages;

import java.util.List;

public class LogStatMessage extends Message {
    public LogStatMessage(List<Object> arglist) {
        super(arglist);
    }

    @Override
    public Message process(int conID) {
        return null;
    }
}
