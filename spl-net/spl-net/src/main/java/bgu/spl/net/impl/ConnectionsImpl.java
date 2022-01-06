package bgu.spl.net.impl;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.srv.bidi.ConnectionHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T> {
    //private Map<Integer,User> ConnectedUsers;
    private Map<Integer, ConnectionHandler<T>> ConnectedUsers;

    private static ConnectionsImpl instance = null;
    private static boolean isDone = false;

    private ConnectionsImpl() {
        ConnectedUsers=new ConcurrentHashMap<>();
    }

    public static ConnectionsImpl getInstance() {
        if(isDone == false)
        {
            synchronized(ConnectionsImpl.class)
            {
                if(isDone == false)
                {
                    instance = new ConnectionsImpl();
                    isDone = true;
                }
            }
        }
        return instance;
    }

    @Override
    public boolean send(int connectionId, Object msg) {
        if(!ConnectedUsers.containsKey(connectionId))
            return false;
        else{
           // System.out.println("message sent to user");
            ConnectedUsers.get(connectionId).send((T)msg);
            return true;
        }
    }

    @Override
    public void broadcast(Object msg) {
        for(ConnectionHandler<T> handler:ConnectedUsers.values()){
            handler.send((T)msg);
        }
    }

    @Override
    public void disconnect(int connectionId)  {
        try {
            ConnectedUsers.remove(connectionId).close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void AddConnection(int conId,ConnectionHandler<T> connectionHandler){
        ConnectedUsers.put(conId,connectionHandler);
        System.out.println("client "+conId+" added to connections");
    }
}
