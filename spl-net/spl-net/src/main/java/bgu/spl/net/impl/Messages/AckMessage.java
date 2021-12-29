package bgu.spl.net.impl.Messages;

public class AckMessage extends Message {
    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
