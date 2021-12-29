package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.NetworkSystemData;

import java.util.List;

public abstract class Message {
    protected List<String> argList;
    protected int opCode;
    protected NetworkSystemData networkSystemData;
    public Message(List<String> argList){
        this.argList=argList;
        networkSystemData=NetworkSystemData.getInstance();
    }
   public abstract byte[]  encode();
   public abstract Message process(int conID);

}
