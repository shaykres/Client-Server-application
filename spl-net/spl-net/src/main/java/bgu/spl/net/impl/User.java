package bgu.spl.net.impl;

import bgu.spl.net.impl.Messages.Message;

import java.net.PortUnreachableException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class User {
    private String name;
    private String password;
    private String birthday;
    private List<String> following;
    private List<String> followers;
    private boolean LogIn;
    private Queue<Message> WaitingMessages;
    private int numPost;
    private List<String> Blocked;

    public User(String name,String password,String birthday){
        this.name=name;
        this.password=password;
        this.birthday=birthday;
        following=new LinkedList<>();
        followers=new LinkedList<>();
        LogIn=false;
        WaitingMessages=new LinkedList<>();
        Blocked=new LinkedList<>();
        numPost=0;
    }

    public void UserLogIn(){
        LogIn=true;
    }

    public void UserLogOut(){
        LogIn=false;
    }

    public boolean IsUserLogIn(){
        return LogIn;
    }

    public String getBirthday(){return birthday;}

    public int getnumPost(){return numPost;}
    public int getNumOfFollowers(){
        return followers.size();
    }
    public int getNumOffollowing(){
        return following.size();
    }

    public void setNumPost(){numPost++;}

    public boolean MatchPass(String pass){
        return password.equals(pass);
    }

    public boolean isFollowing(String userName){
        return following.contains(userName);
    }

    public void follow(String userName){
        following.add(userName);
    }

    public void Unfollow(String userName){
        following.remove(userName);
    }

    public void RemoveFollower(String userName){
        followers.remove(userName);
    }

    public String getUserName(){return name;}

    public List<String> getFollowers(){
        return followers;
    }

    public void AddWaitMessage(Message message){
        WaitingMessages.add(message);
    }

    public void BlockUser(String userName){
        Blocked.add(userName);
        following.remove(userName);
        followers.remove(userName);
    }

    public boolean isUserBlock(String userName){
        return Blocked.contains(userName);
    }

    public Queue<Message> getWaitingMessages(){
        return  WaitingMessages;
    }

}
