package bgu.spl.net.srv;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.ConnectionsImpl;
import bgu.spl.net.impl.Messages.Message;
import bgu.spl.net.srv.bidi.ConnectionHandler;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BlockingConnectionHandler<T> implements Runnable, ConnectionHandler<T> {

    private final BidiMessagingProtocol<T> protocol;
    private final MessageEncoderDecoder<T> encdec;
    private final Socket sock;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private volatile boolean connected = true;
    private int connectionID;
    private ConnectionsImpl<T> connections;
    Object lock=new Object();

    public BlockingConnectionHandler(int connectionID, Socket sock, MessageEncoderDecoder<T> reader, BidiMessagingProtocol<T> protocol, ConnectionsImpl<T> connections) {
        this.sock = sock;
        this.encdec = reader;
        this.protocol = protocol;
        this.connectionID = connectionID;
        this.connections=connections;
        this.connections.AddConnection(connectionID, this);
        this.protocol.start(connectionID, connections);
    }

    @Override
    public void run() {
        try (Socket sock = this.sock) { //just for automatic closing
            int read;
            in = new BufferedInputStream(sock.getInputStream());
            out = new BufferedOutputStream(sock.getOutputStream());
            while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0) {
                T nextMessage = encdec.decodeNextByte((byte) read);
                if (nextMessage != null) {
                    protocol.process(nextMessage);
                    if (!sock.isClosed())
                        in = new BufferedInputStream(sock.getInputStream());
                }
            }
            if(protocol.shouldTerminate()){
                connections.disconnect(connectionID);
                
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        connected = false;
        sock.close();
    }

    @Override
    //if user got number of notifications at the same time from different clients
    public synchronized void send(T msg) {
            try {
                out.write(encdec.encode(msg));
                out.flush();
                System.out.println("message sent to user " + connectionID);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
