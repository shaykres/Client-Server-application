package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class FollowMessage extends Message {
    private boolean follow;
    private String userName;

    public FollowMessage(List<Object> arglist) {
        super(arglist);
        opCode=4;
        if(arglist.get(0).equals("0"))
            follow=true;
        else
            follow=false;
        userName=(String)arglist.get(1);
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.FollowClient(conID,follow,userName);
        if(success)
            return new AckMessage(new LinkedList<>());
        return new ErrorMessage();
    }
}
