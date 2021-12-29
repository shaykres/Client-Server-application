package bgu.spl.net.impl.Messages;

import java.util.List;

public class LoginMessage extends Message {
    public LoginMessage(List<String> arglist) {
        super(arglist);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
