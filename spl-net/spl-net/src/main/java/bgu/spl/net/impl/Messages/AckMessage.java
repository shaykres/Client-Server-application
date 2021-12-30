package bgu.spl.net.impl.Messages;

import java.util.List;

public class AckMessage extends Message {

    public AckMessage(List<String> argList) {
        super(argList);
    }

    @Override
    public Message process(int conID) {
        return null;
    }
}
