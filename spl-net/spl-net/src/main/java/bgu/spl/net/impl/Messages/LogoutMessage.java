package bgu.spl.net.impl.Messages;

import java.util.List;

public class LogoutMessage extends Message {
    public LogoutMessage(List<String> arglist) {
        super(arglist);
        opCode=3;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.LogOutClient(conID);
        if(success)
            return new AckMessage();
        return new ErrorMessage();
    }
}
