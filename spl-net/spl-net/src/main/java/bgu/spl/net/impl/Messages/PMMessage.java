package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.NetworkSystemData;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PMMessage extends Message{
    private String userName;
    private String content;
    //private String _dateAndTime;
    public PMMessage(List<Object> arglist) {
        super(arglist);
        userName=(String)arglist.get(0);
        content="";
        for(int i=1;i<arglist.size();i++)
            content+=" "+(String)arglist.get(i);
        opCode=6;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd" );
        Date dateAndTime = new Date(System.currentTimeMillis());
        content+=" "+formatter.format(dateAndTime);
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
        boolean registerdUser=networkSystemData.IsUserRegistered(userName);
        FilterMessage();
        boolean success=networkSystemData.SendPrivateMessage(conID,userName,this);
        List l=new LinkedList();
        l.add(this);
        if(!registerdUser) {
            l.add(userName+" isn't applicable for private messages");
            return new ErrorMessage(l);
        }
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }

    private void FilterMessage(){
        String newContent="";
        String[] splited = content.split(" ");
        for(int i=0; i<splited.length; i++){
            if(networkSystemData.isFilterWord(splited[i]))
                newContent+="<filtered> ";
            else
                newContent+=splited[i]+" ";
        }
        content=newContent;
    }

    public String getContent(){
        return content;
    }
}
