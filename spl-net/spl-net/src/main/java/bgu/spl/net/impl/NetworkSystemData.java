package bgu.spl.net.impl;

import bgu.spl.net.impl.Messages.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkSystemData {

    private static NetworkSystemData instance = null;
    private static boolean isDone = false;

    private Map<String,User> SystemUsers;//All users registered since the beginning of the program
    private Map<Integer,User> ConUsers;//All users connected to server
    private List<Message> Messages;


    private NetworkSystemData() {
        SystemUsers=new ConcurrentHashMap<>();
        ConUsers=new ConcurrentHashMap<>();
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

    public boolean RegisterClient(String userName,User user,int conID){
        if(SystemUsers.containsKey(userName))
            return false;
        SystemUsers.put(userName,user);
        ConUsers.put(conID,user);
        return true;
    }

    public boolean LogInClient(String userName,String password){
        if(!SystemUsers.containsKey(userName))
            return false;
        if(!SystemUsers.get(userName).IsUserLogIn())
            return false;
        if(! SystemUsers.get(userName).MatchPass(password))
            return false;
        SystemUsers.get(userName).UserLogIn();
        return true;
    }

    public boolean LogOutClient(int conID){
        if(!ConUsers.containsKey(conID))
            return false;
       ConUsers.get(conID).UserLogOut();
       ConUsers.remove(conID);
       return true;
    }

    public boolean FollowClient(int conID,boolean tofollow,String userName){
        if(!ConUsers.containsKey(conID))
            return false;
        if(tofollow&&!ConUsers.get(conID).isFollowing(userName)){
            ConUsers.get(conID).follow(userName);
            return true;
        }
        if(!tofollow&&ConUsers.get(conID).isFollowing(userName)){
            ConUsers.get(conID).Unfollow(userName);
            return true;
        }
        return false;
    }


}



