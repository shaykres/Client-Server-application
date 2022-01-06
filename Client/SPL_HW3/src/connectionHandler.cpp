#include "../include/connectionHandler.h"
 
using boost::asio::ip::tcp;

using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::string;
ConnectionHandler::ConnectionHandler(string host, short port): host_(host), port_(port), io_service_(), socket_(io_service_){}
    
ConnectionHandler::~ConnectionHandler() {
    close();
}
 
bool ConnectionHandler::connect() {
    std::cout << "Starting connect to " 
        << host_ << ":" << port_ << std::endl;
    try {
		tcp::endpoint endpoint(boost::asio::ip::address::from_string(host_), port_); // the server endpoint

		boost::system::error_code error;
		socket_.connect(endpoint, error);
		if (error) {
            throw boost::system::system_error(error);
        }
    }
    catch (std::exception& e) {
        std::cerr << "Connection failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }

    return true;
}
 
bool ConnectionHandler::getBytes(char bytes[], unsigned int bytesToRead) {
    size_t tmp = 0;
	boost::system::error_code error;
    try {
        while (!error && bytesToRead > tmp ) {
			tmp += socket_.read_some(boost::asio::buffer(bytes+tmp, bytesToRead-tmp), error);
			//std::cout <<"i recive:"<<tmp<< std::endl;			
        }
		if(error)
			throw boost::system::system_error(error);
    } catch (std::exception& e) {
        std::cerr << "recv failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }
    return true;
}

bool ConnectionHandler::sendBytes(const char bytes[], int bytesToWrite) {
    int tmp = 0;
	boost::system::error_code error;
    try {
      // for(int i=0;i<bytesToWrite;i++)
       //     std::cout << bytes[i] <<"hello"<< std::endl;
        while (!error && bytesToWrite > tmp ) {
			tmp += socket_.write_some(boost::asio::buffer(bytes + tmp, bytesToWrite - tmp), error);
        }
		if(error)
			throw boost::system::system_error(error);
    } catch (std::exception& e) {
        std::cerr << "recv failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }
    return true;
}
 
bool ConnectionHandler::getLine(std::string& line) {
   // std::cout << "get line" << std::endl;
    return getFrameAscii(line, ';');
}

bool ConnectionHandler::sendLine(std::string& line) {
   // std::cout << "sent line" << std::endl;
    return sendFrameAscii(line, ';');
}
 
bool ConnectionHandler::getFrameAscii(std::string& frame, char delimiter) {
    char* byte= new char[2];
    char ch;
    // Stop when we encounter the null character. 
    // Notice that the null character is not appended to the frame string.
    try {
        //std::cout << "i before opcode" << std::endl;
        getBytes(&ch, 1);
        byte[0]=ch;
        getBytes(&ch, 1);
        byte[1]=ch;
	//std::cout << "i after opcode" << std::endl;
        short opcode=bytesToShort(byte);
	//std::cout << "my opcode is:"<<opcode << std::endl;
        if(opcode==10||opcode==11){
            getBytes(&ch, 1);
            byte[0]=ch;
            getBytes(&ch, 1);
            byte[1]=ch;
           // std::cout << opcode << std::endl;
            short subject=bytesToShort(byte);
           //std::cout << subject << std::endl;
            if(opcode==10){
               if(subject==7||subject==8) {
                   sevenOr8(frame, subject);
                   return true;
               }
               else
                   frame+="ACK "+std::to_string(subject);
            }
            if(opcode==11)
                frame+="ERROR "+std::to_string(subject);
            if(delimiter != ch)
                frame+=" ";
        }
        if(opcode==9){
            frame+="NOTIFICATION ";
	}
        if(opcode==12)
            frame+="BLOCK ";
		do{
			getBytes(&ch, 1);
			frame.append(1, ch);
        }while (delimiter != ch);
    } catch (std::exception& e) {
        std::cerr << "recv failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }
    return true;
}
 
bool ConnectionHandler::sendFrameAscii(const std::string& frame, char delimiter) {
	//split /n
    bool result=sendBytes(frame.c_str(),frame.length());
	if(!result) return false;
	return sendBytes(&delimiter,1);
}
 
// Close down the connection properly.
void ConnectionHandler::close() {
    try{
        socket_.close();
    } catch (...) {
        std::cout << "closing failed: connection already closed" << std::endl;
    }
}

short ConnectionHandler::bytesToShort(char *bytesArr) {
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}

void ConnectionHandler::sevenOr8(std::string& frame,short subject) {
    char* byte= new char[2];
    char ch;
    //bool enter= true;
    while (ch!=';'){
    frame+=ch;
    getBytes(&ch, 1);
    byte[0]=ch;
    getBytes(&ch, 1);
    byte[1]=ch;
    short age=bytesToShort(byte);
    getBytes(&ch, 1);
    byte[0]=ch;
    getBytes(&ch, 1);
    byte[1]=ch;
    short numPost=bytesToShort(byte);
    getBytes(&ch, 1);
    byte[0]=ch;
    getBytes(&ch, 1);
    byte[1]=ch;
    short numfollowers=bytesToShort(byte);
    getBytes(&ch, 1);
    byte[0]=ch;
    getBytes(&ch, 1);
    byte[1]=ch;
    short numfollowing=bytesToShort(byte);
    frame+="ACK "+std::to_string(subject)+" "+std::to_string(age)+" "+std::to_string(numPost)+" "+std::to_string(numfollowers)+" "+std::to_string(numfollowing);
    getBytes(&ch, 1);
    }
    frame+=" ";
}


