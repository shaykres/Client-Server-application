package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class PostMessage extends Message {
    private String content;
    private String userNameSentTo;
    public PostMessage(List<Object> arglist) {
        super(arglist);
        opCode=5;
        content="";
        for (Object b:arglist) {
            content+=" "+(String)b;
        }
        userNameSentTo="";
        if(content.contains("@")){
            int StartUserName=content.indexOf("@")+1;
            userNameSentTo=content.substring(StartUserName);
            userNameSentTo=userNameSentTo.substring(0,userNameSentTo.indexOf(" "));
        }
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
       boolean success=true;
       if(userNameSentTo!=""){
         success=networkSystemData.PostMessage(conID,this,userNameSentTo);
       }
       else
           success=networkSystemData.PostMessage(conID,this);
       List l=new LinkedList();
       l.add(this);
        if(success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }

    public String getContent(){
        return content;
    }

}

