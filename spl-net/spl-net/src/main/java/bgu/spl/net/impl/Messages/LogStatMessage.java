package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class LogStatMessage extends Message {

    private List<User> users;

    public LogStatMessage(List<Object> arglist) {
        super(arglist);
        opCode=7;
        users=new LinkedList<>();
    }

    @Override
    public byte[] encode() {
        byte[] message=new byte[13*users.size()];
        if(users.size()==0){
            byte[]noting=new byte[4];
            byte[]opcodeack=shortToBytes((short)10);
            noting[0]=opcodeack[0];
            noting[1]=opcodeack[1];
            byte[]opcode=shortToBytes(opCode);
            noting[2]=opcode[0];
            noting[3]=opcode[1];
            return noting;
        }
        for (User u: users) {
            byte[]opcodeack=shortToBytes((short)10);
            message[0]=opcodeack[0];
            message[1]=opcodeack[1];
            byte[]opcode=shortToBytes(opCode);
            message[2]=opcode[0];
            message[3]=opcode[1];
            //age
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = u.getBirthday();
            LocalDate birthday = LocalDate.parse(date, formatter);
            LocalDate now = LocalDate.now();
            int age=Period.between(birthday, now).getYears();
            byte[]byteage=shortToBytes((short)age);
            message[4]=byteage[0];
            message[5]=byteage[1];
            //*****
            byte[]numOfPost=shortToBytes((short)u.getnumPost());
            message[6]=numOfPost[0];
            message[7]=numOfPost[1];
            byte[]numOffollowers=shortToBytes((short)u.getNumOfFollowers());
            message[8]=numOffollowers[0];
            message[9]=numOffollowers[1];
            byte[]numOffollowing=shortToBytes((short)u.getNumOffollowing());
            message[10]=numOffollowing[0];
            message[11]=numOffollowing[1];
            message[12]=';';
        }
        return message;
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.LogStat(conID,users);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return this;
        return new ErrorMessage(l);
    }
}
