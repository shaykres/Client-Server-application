package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.User;

import javax.print.DocFlavor;
import java.util.List;

public class RegisterMessage extends Message {

    private String userName;
    private String password;
    private String birthday;

    public RegisterMessage(List<String> argList) {
        super(argList);
        opCode=1;
        userName=argList.get(0);
        password=argList.get(1);
        birthday=argList.get(2);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Message process(int conID) {
        User user=new User(userName,password,birthday);
        boolean success=networkSystemData.RegisterClient(userName,user,conID);
        if(success)
            return new AckMessage();
        return new ErrorMessage();
    }
}
