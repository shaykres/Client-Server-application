package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.User;

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.List;

public class RegisterMessage extends Message {

    private String userName;
    private String password;
    private String birthday;

    public RegisterMessage(List<Object> argList) {
        super(argList);
       // System.out.println("i build register");
        opCode=1;
        userName=(String)argList.get(0);
        password=(String)argList.get(1);
        birthday=(String)argList.get(2);
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
       // System.out.println("i am in process");
        User user=new User(userName,password,birthday);
        boolean success=networkSystemData.RegisterClient(userName,user,conID);
        List l=new LinkedList();
        l.add(this);
        //System.out.println("I success?"+success);
        if(success) {
           // System.out.println("i am in succsess");
            return new AckMessage(l);
        }
        return new ErrorMessage(l);
    }
}
