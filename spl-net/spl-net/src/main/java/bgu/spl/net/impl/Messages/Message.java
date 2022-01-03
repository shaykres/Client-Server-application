package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.NetworkSystemData;

import java.util.List;

public abstract class Message {
    protected List<Object> argList;
    protected short opCode;
    protected NetworkSystemData networkSystemData;
    public Message(List<Object> argList){
        this.argList=argList;
        networkSystemData=NetworkSystemData.getInstance();
    }
   public abstract byte[]  encode();
   public abstract Message process(int conID);
    public byte[] shortToBytes(short num) {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
    @Override
    public String toString(){
        return " "+opCode;
    }
}
