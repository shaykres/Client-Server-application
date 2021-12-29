package bgu.spl.net.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private String birthday;
    private List<User> following;

    public User(String name,String password,String birthday){
        this.name=name;
        this.password=password;
        this.birthday=birthday;
        following=new LinkedList<>();

    }

}
