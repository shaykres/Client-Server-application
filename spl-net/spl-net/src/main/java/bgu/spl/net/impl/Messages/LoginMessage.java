package bgu.spl.net.impl.Messages;

import java.util.List;

public class LoginMessage extends Message {
    private String userName;
    private String password;
    private char captcha;
    public LoginMessage(List<String> arglist) {
        super(arglist);
        opCode=2;
        userName=arglist.get(0);
        password=arglist.get(1);
        //utilize captcha
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.LogInClient(userName,password);
        if(success)
            return new AckMessage();
        return new ErrorMessage();
    }
}
