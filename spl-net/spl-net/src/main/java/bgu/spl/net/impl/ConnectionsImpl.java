package bgu.spl.net.impl;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.srv.bidi.ConnectionHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl implements Connections {
    //private Map<Integer,User> ConnectedUsers;
    private Map<Integer, ConnectionHandler<Message>> ConnectedUsers;

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
            ConnectedUsers.get(connectionId).send((Message)msg);
            return true;
        }
    }

    @Override
    public void broadcast(Object msg) {

    }

    @Override
    public void disconnect(int connectionId) {
        ConnectedUsers.remove(connectionId);
    }
}
