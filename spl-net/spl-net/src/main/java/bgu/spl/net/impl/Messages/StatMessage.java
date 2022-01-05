package bgu.spl.net.impl.Messages;

import bgu.spl.net.impl.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class StatMessage extends Message {
    private List<User> users;
    private List<String> usersName;

    public StatMessage(List<Object> arglist) {
        super(arglist);
        opCode=8;
        usersName=new LinkedList<>();
        users=new LinkedList<>();
        String listUsers=(String)arglist.get(0);
        String[] parts = listUsers.split("\\|");
        for(int i=0;i<parts.length;i++) {
            usersName.add(parts[i]);
        }
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
        int i=0;
        byte[]opcodeack=shortToBytes((short)10);
        message[0]=opcodeack[0];
        message[1]=opcodeack[1];
        byte[]opcode=shortToBytes(opCode);
        message[2]=opcode[0];
        message[3]=opcode[1];
        for (User u: users) {
            //age
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = u.getBirthday();
            LocalDate birthday = LocalDate.parse(date, formatter);
            LocalDate now = LocalDate.now();
            int age=Period.between(birthday, now).getYears();
            byte[]byteage=shortToBytes((short)age);
            message[4+9*i]=byteage[0];
            message[5+9*i]=byteage[1];
            //*****
            byte[]numOfPost=shortToBytes((short)u.getnumPost());
            message[6+9*i]=numOfPost[0];
            message[7+9*i]=numOfPost[1];
            byte[]numOffollowers=shortToBytes((short)u.getNumOfFollowers());
            message[8+9*i]=numOffollowers[0];
            message[9+9*i]=numOffollowers[1];
            byte[]numOffollowing=shortToBytes((short)u.getNumOffollowing());
            message[10+9*i]=numOffollowing[0];
            message[11+9*i]=numOffollowing[1];
            message[12+9*i]='\n';
            i++;
        }
        message[12+9*(i-1)]=';';
        return message;
    }

    @Override
    public Message process(int conID) {
        boolean success=networkSystemData.Stat(conID,usersName,users);
        List l=new LinkedList();
        l.add(this);
        if(success)
            return this;
        return new ErrorMessage(l);
    }
}
