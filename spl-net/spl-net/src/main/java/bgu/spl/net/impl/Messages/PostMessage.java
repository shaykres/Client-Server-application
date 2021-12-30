package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class PostMessage extends Message {
    private String Content;
    private String userNameSentTo;
    public PostMessage(List<Object> arglist) {
        super(arglist);
        Content=(String)arglist.get(0);
        userNameSentTo="";
        if(Content.contains("@")){
            int StartUserName=Content.indexOf("@")+1;
            userNameSentTo=Content.substring(StartUserName);
            userNameSentTo=userNameSentTo.substring(0,userNameSentTo.indexOf(" "));
        }
    }

    @Override
    public Message process(int conID) {
       boolean success=true;
       if(userNameSentTo!=""){
         success=networkSystemData.PostMessage(conID,this,userNameSentTo);
       }
       else
           success=networkSystemData.PostMessage(conID,this);

        if(success)
            return new AckMessage(new LinkedList<>());
        return new ErrorMessage();
    }

}
