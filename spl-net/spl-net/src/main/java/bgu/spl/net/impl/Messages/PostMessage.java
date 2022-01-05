package bgu.spl.net.impl.Messages;

import java.util.LinkedList;
import java.util.List;

public class PostMessage extends Message {
    private String content;
    private List<String> userNameSentTo;

    public PostMessage(List<Object> arglist) {
        super(arglist);
        opCode = 5;
        content = "";
        for (Object b : arglist) {
            content += " " + (String) b;
        }
        userNameSentTo = new LinkedList<>();
        String temp = content;
        boolean endoftemp = false;
        while (temp.contains("@")) {
            endoftemp = false;
            int StartUserName = temp.indexOf("@") + 1;
            String userName = temp.substring(StartUserName);
            temp = temp.substring(StartUserName);
            int endUserName = temp.indexOf(" ");
            if (endUserName == -1) {
                endUserName = temp.length();
                endoftemp = true;
            }
            userName = temp.substring(0, endUserName);
            System.out.println(userName);
            userNameSentTo.add(userName);
            if (!endoftemp)
                temp = temp.substring(endUserName + 1);
        }
    }

    @Override
    public byte[] encode() {
        return shortToBytes(opCode);
    }

    @Override
    public Message process(int conID) {
        boolean success = true;
        if (userNameSentTo.size() > 0) {
            success = networkSystemData.PostMessage(conID, this, userNameSentTo);
        } else
            success = networkSystemData.PostMessage(conID, this);
        List l = new LinkedList();
        l.add(this);
        if (success)
            return new AckMessage(l);
        return new ErrorMessage(l);
    }

    public String getContent() {
        return content;
    }

}

