package bgu.spl.net.impl;

import bgu.spl.net.impl.Messages.Message;

import java.util.LinkedList;
import java.util.List;

public class NetworkSystemData {

    private static NetworkSystemData instance = null;
    private static boolean isDone = false;

    private List<User> SystemUsers;
    private List<Message> Messages;


    private NetworkSystemData() {
        SystemUsers=new LinkedList<>();
        Messages=new LinkedList<>();
    }


    public static NetworkSystemData getInstance() {
        if(isDone == false)
        {
            synchronized(NetworkSystemData.class)
            {
                if(isDone == false)
                {
                    instance = new NetworkSystemData();
                    isDone = true;
                }
            }
        }
        return instance;
    }
}



