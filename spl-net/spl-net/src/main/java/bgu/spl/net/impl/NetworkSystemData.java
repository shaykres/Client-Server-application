package bgu.spl.net.impl;

import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.impl.Messages.NotificationMessage;

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
    private ConnectionsImpl connections;
    private List<String> filterWords;


    private NetworkSystemData() {
        SystemUsers=new ConcurrentHashMap<>();
        ConUsers=new ConcurrentHashMap<>();
        Messages=new LinkedList<>();
        connections=ConnectionsImpl.getInstance();
        filterWords=new LinkedList<>();
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

    public boolean LogInClient(int conId,String userName,String password,char captcha){
        if(!SystemUsers.containsKey(userName))
            return false;
        if(SystemUsers.get(userName).IsUserLogIn())
            return false;
        if(! SystemUsers.get(userName).MatchPass(password))
            return false;
        if(captcha=='0')
            return false;
        SystemUsers.get(userName).UserLogIn();
        while (!SystemUsers.get(userName).getWaitingMessages().isEmpty()){
            connections.send(conId,SystemUsers.get(userName).getWaitingMessages().poll());
        }
        return true;
    }

    public boolean LogOutClient(int conID){
        if(!ConUsers.containsKey(conID))
            return false;
       ConUsers.get(conID).UserLogOut();
       ConUsers.remove(conID);
       return true;
    }

    public boolean FollowClient(int conId,boolean tofollow,String userName){
        if(!ConUsers.containsKey(conId)||!SystemUsers.containsKey(userName))
            return false;
        if(tofollow&&!ConUsers.get(conId).isFollowing(userName)){
            if(SystemUsers.get(userName).isUserBlock(ConUsers.get(conId).getUserName()))
                return false;
            ConUsers.get(conId).follow(userName);
            return true;
        }
        if(!tofollow&&ConUsers.get(conId).isFollowing(userName)){
            ConUsers.get(conId).Unfollow(userName);
            return true;
        }
        return false;
    }

    public boolean PostMessage(int conId,Message post,String userNameToSent){
        if(IsUserLogIn(userNameToSent)&&IsUserLogIn(conId)){
            int conIdToSent=ClientConId(userNameToSent);
           // connections.send(conIdToSent,post);
            List<Object> l=new LinkedList<>();
            l.add(ConUsers.get(conId).getUserName());
            l.add(post);
            NotificationMessage notificationMessage=new NotificationMessage(l);
            if(SystemUsers.get(userNameToSent).isUserBlock(ConUsers.get(conId).getUserName()))
                return false;
            connections.send(conIdToSent,notificationMessage);
            for(String follower:ConUsers.get(conId).getFollowers()){
                conIdToSent=ClientConId(follower);
                if(conIdToSent==-1)
                    SystemUsers.get(follower).AddWaitMessage(notificationMessage);
                else
                    connections.send(conIdToSent,notificationMessage);
            }
            ConUsers.get(conId).setNumPost();
            Messages.add(post);
            ConUsers.get(conId).setNumPost();
            return true;
        }
        return false;
    }
    public boolean LogStat(int ConId,List<User> users) {
        if(IsUserLogIn(ConId)) {
            for(int i=0;i<ConUsers.size();i++)
                users.add(ConUsers.get(i));
            return true;
        }
        return false;
    }
    public boolean Stat(int ConId,List<String> usernames,List<User> users) {
        if(IsUserLogIn(ConId)) {
            for(int i=0;i<usernames.size();i++) {
               if(!SystemUsers.containsKey(usernames.get(i)))
                   return false;
               users.add(SystemUsers.get(usernames.get(i)));
            }
            return true;
        }
        return false;
    }
    public boolean PostMessage(int ConId,Message post){
        List<Object> l=new LinkedList<>();
        l.add(ConUsers.get(ConId).getUserName());
        l.add(post);
        NotificationMessage notificationMessage=new NotificationMessage(l);
        if(IsUserLogIn(ConId)) {
            for (String follower : ConUsers.get(ConId).getFollowers()) {
                int conIdToSent = ClientConId(follower);
                if (conIdToSent == -1)
                    SystemUsers.get(follower).AddWaitMessage(notificationMessage);
                else
                    connections.send(conIdToSent, notificationMessage);
            }
            Messages.add(post);
            return true;
        }
        return false;
    }
    public boolean IsUserLogIn(String userName)
    {
        return SystemUsers.get(userName).IsUserLogIn();
    }

    public boolean IsUserLogIn(int ConId)
    {
        return ConUsers.get(ConId).IsUserLogIn();
    }

    public int ClientConId(String userName){
        for(int id :ConUsers.keySet()){
            if(ConUsers.get(id).getUserName().equals(userName))
                return id;
        }
        return -1;
    }

    public boolean SendPrivateMessage(int conId,String userName,Message pm){
        if(!ConUsers.containsKey(conId))
            return false;
        if(!IsUserLogIn(conId))
            return false;
      if(!IsUserRegistered(userName))
          return false;
      if(!ConUsers.get(conId).isFollowing(userName))
          return false;
        if(SystemUsers.get(userName).isUserBlock(ConUsers.get(conId).getUserName()))
            return false;
        List<Object> l=new LinkedList<>();
        l.add(ConUsers.get(conId).getUserName());
        l.add(pm);
        NotificationMessage notificationMessage=new NotificationMessage(l);
        int conIdToSent = ClientConId(userName);
        if (conIdToSent == -1)
            SystemUsers.get(userName).AddWaitMessage(notificationMessage);
        else
            connections.send(conIdToSent, notificationMessage);
        Messages.add(pm);
        return true;
    }

    public boolean IsUserRegistered(String userName){
        return SystemUsers.containsKey(userName);
    }

    public boolean isFilterWord(String word){
        return filterWords.contains(word);
    }

    public boolean BlockUser(int conId,String userToBlock){
        if(!SystemUsers.containsKey(userToBlock))
            return false;
        if(!IsUserLogIn(conId))
            return false;
        ConUsers.get(conId).BlockUser(userToBlock);
        SystemUsers.get(userToBlock).Unfollow( ConUsers.get(conId).getUserName());
        SystemUsers.get(userToBlock).RemoveFollower(ConUsers.get(conId).getUserName());
        return true;
    }

}



