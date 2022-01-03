package bgu.spl.net.impl;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.impl.Messages.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageEncoderDecoderImpl implements MessageEncoderDecoder<Message> {
    //private final ByteBuffer lengthBuf = ByteBuffer.allocate(2);
    private int byteDecode;
    private byte[] opcode=new byte[2];
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len;
    private List<Object> arglist;
    private int opCode;
    private boolean shouldreset;

    public MessageEncoderDecoderImpl() {
        arglist = new ArrayList<>();
        opCode = -1;
        byteDecode=0;
        len=0;
        shouldreset=false;
    }

    @Override
    public Message decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison
        if(shouldreset) {
            reset();
        }
        else {
            if (opCode == -1) {
                opcode[byteDecode] = nextByte;
                System.out.println("byteDecode: "+byteDecode+"nextByte"+nextByte);
                byteDecode++;
                if (byteDecode == 2) {
                    opCode = bytesToShort(opcode);
                    System.out.println(opCode);
                }
            } else {
                if (nextByte != ';')
                    pushByte(nextByte);
                else {
                    popString();
                    shouldreset = true;
                    return myCommand(opCode);
                }
            }
        }
        return null;
    }

    @Override
    public byte[] encode(Message message) {
        System.out.println("i am in encode");
        return message.encode();
    }


    private void pushByte(byte nextByte) {
      //  System.out.println("i in len "+ len+" i am "+nextByte);
        if (nextByte == ' ') {
            popString();
        } else {
            if (len >= bytes.length) {
                bytes = Arrays.copyOf(bytes, len * 2);
            }
            bytes[len++] = nextByte;
        }
    }

    private void popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        arglist.add(result);
    }

    public short bytesToShort(byte[] byteArr) {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    private Message myCommand(int opCode) {
        switch (opCode) {
            case 1:
                return new RegisterMessage(arglist);

            case 2:
                return new LoginMessage(arglist);

            case 3:
                return new LogoutMessage(arglist);

            case 4:
                return new FollowMessage(arglist);

            case 5:
                return new PostMessage(arglist);

            case 6:
                return new PMMessage(arglist);

            case 7:
                return new LogStatMessage(arglist);

            case 8:
                return new StatMessage(arglist);

            case 12:
                return new BlockMessage(arglist);

        }
        return null;
    }

    public void reset(){
        arglist = new ArrayList<>();
        opCode = -1;
        byteDecode=0;
        len=0;
        shouldreset=false;
        byte[] opcode=new byte[2];
        byte[] bytes = new byte[1 << 10]; //start with 1k
    }

}
