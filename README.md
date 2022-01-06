1) RUN THE CODE
COMPILE SERVER ON LINUX- 
mvn clean
mvn package
mvn exec:java -Dexec.mainClass=bgu.spl.net.Main.mainTCP //for running BaseServer
mvn exec:java -Dexec.mainClass=bgu.spl.net.Main.mainReactor //for running Reactor

COMPILE Client ON LINUX- 
make clean
make 
bin/BGSclient 

1.1) command line for each server is written obove
definition for number of threads and port for Reactor - from mainReactor Class
definition for port for BaseServer - from mainTCP Class
definition for host and port for Client- from echoClient Class at cpp

1.2)All command messages are in CAPITAL LETTERS, for example - REGISTER, LOGIN, LOGOUT, FOLLOW, BLOCK ext.
birthday is written as follows: DD-MM-YYYY

2)filter words are located in NetworkSystemData Class, path:bgu.spl.net.impl.NetworkSystemData
at the constructor of the class you can add words to the filter word list.
