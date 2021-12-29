package bgu.spl.net.impl.Messages;

import java.util.List;

public class RegisterMessage extends Message {
    int opcode;
    public RegisterMessage(List<String> argList) {
        super(argList);
        opcode=1;
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
