package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class BlockMessage extends Message{
    private String UserNameToBlock;
    public BlockMessage(List<Object> argList) {
        super(argList);
        UserNameToBlock=(String)argList.get(0);

    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.BlockUser(conID,UserNameToBlock);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }
}
