package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class LogoutMessage extends Message {
    public LogoutMessage(List<Object> arglist) {
        super(arglist);
        opCode=3;
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.LogOutClient(conID);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }
}
