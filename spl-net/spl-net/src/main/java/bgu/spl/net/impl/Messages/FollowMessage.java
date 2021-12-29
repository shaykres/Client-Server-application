package bgu.spl.net.impl.Messages;

import java.util.List;

public class FollowMessage extends Message {
    private boolean follow;
    private String userName;

    public FollowMessage(List<String> arglist) {
        super(arglist);
        opCode=4;
        if(arglist.get(0).equals("0"))
            follow=true;
        else
            follow=false;
        userName=arglist.get(1);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.FollowClient(conID,follow,userName);
        if(success)
            return new AckMessage();
        return new ErrorMessage();
    }
}
