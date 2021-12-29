#include <stdlib.h>
#include "../include/connectionHandler.h"
#include "../include/Task.h"

void shortToBytes(short num, char* bytesArr);

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }
    std::string host = argv[1];
    short port = atoi(argv[2]);
    std::map<std::string ,short> CommandMap;
    CommandMap["REGISTER"]=1;
    CommandMap["LOGIN"]=2;
    CommandMap["LOGOUT"]=3;
    CommandMap["FOLLOW"]=4;
    CommandMap["POST"]=5;
    CommandMap["PM"]=6;
    CommandMap["LOGSTAT"]=7;
    CommandMap["STAT"]=8;
//    CommandMap["NOTIFICATION"]=9;
//    CommandMap["ACK"]=10;
//    CommandMap["ERROR"]=11;
//    CommandMap["BLOCK"]=12;

    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

    std::mutex mutex;
    Task readFromSocketTask(1,connectionHandler,mutex);
    std::thread th1(&Task::run,&readFromSocketTask);
	//From here we will see the rest of the ehco client implementation:
    while (1) {
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
		std::string line(buf);
        std::vector<std::string> words;
        int endofkeywork=line.find('\n');
        std::string keyword=line.substr(0,endofkeywork-1);
        int opcode=CommandMap[keyword];
        line=line.substr(endofkeywork+1);
        char* bytes= new char[2];
        shortToBytes(opcode,bytes);
        std::string strOptCode = "";
        strOptCode.append(1,bytes[0]);
        strOptCode.append(1,bytes[1]);
        line=strOptCode+line;
//        bytes[0]=opcode;
//        short result= bytesToShort(bytes);
//        line=result+"\n"+line;
        int len=line.length();
        if (!connectionHandler.sendLine(line)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
		// connectionHandler.sendLine(line) appends '\n' to the message. Therefor we send len+1 bytes.
        std::cout << "Sent " << len+1 << " bytes to server" << std::endl;

        // We can use one of three options to read data from the server:
        // 1. Read a fixed number of characters
        // 2. Read a line (up to the newline character using the getline() buffered reader
        // 3. Read up to the null character
        th1.join();
//        std::string answer;
        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
//        if (!connectionHandler.getLine(answer)) {
//            std::cout << "Disconnected. Exiting...\n" << std::endl;
//            break;
//        }
//
//		len=answer.length();
		// A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
		// we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
//        answer.resize(len-1);
//        std::cout << "Reply: " << answer << " " << len << " bytes " << std::endl << std::endl;
//        if (answer == "bye") {
//            std::cout << "Exiting...\n" << std::endl;
//            break;
//        }
    }
    return 0;
}

void shortToBytes(short num, char* bytesArr){
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}