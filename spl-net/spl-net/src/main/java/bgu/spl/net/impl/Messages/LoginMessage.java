package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class LoginMessage extends Message {
    private String userName;
    private String password;
    private char captcha;
    public LoginMessage(List<Object> arglist) {
        super(arglist);
        opCode=2;
        userName=(String)arglist.get(0);
        password=(String)arglist.get(1);
        //utilize captcha
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.LogInClient(userName,password);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }
}
