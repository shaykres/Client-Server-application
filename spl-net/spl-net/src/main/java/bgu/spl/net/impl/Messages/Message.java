package bgu.spl.net.impl.Messages;

import java.util.List;

public abstract class Message {
    protected List<String> argList;
    public Message(List<String> argList){
        this.argList=argList;
    }
    public abstract byte[]  encode();
   public abstract Message process(int conID);

}
