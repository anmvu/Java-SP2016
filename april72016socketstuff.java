/*
April 7, 2016
More server stuff

*/
//Part 1
public class April72016ServerSocket{

	//172.16.13.11
	public static void main(String[] args){
		try{
			ServerSocket ss = new ServerSocket(1999);
             //to run go to terminal and type telnet ip address port
			//not use for communication
			//if we accept - waits for connection to be established(client) and when connected, the socket is passed as a return
			//do not need to connect socket but can get input stream and output stream from it
			while(true){
				Socket sock = ss.accept();//now connected to a client will not return from call until connected to client from the other side of the connection
				//can only service one client

				//moved to processClient
				// Scanner sin =  new Scanner(sock.getInputStream());
				// PrintStream sout = new PrintStream(sock.getOutputStream());
				// while(sock.isConnected()){//
				// 	//as long as connected - taking data and sending it back
				// 	sout.print("Welcome to the Echo Server!\r\n");
				// 	String line = sin.nextLine();
				// 	sout.print(line+"\r\n");
				// 	System.out.println(sock.getInetAddress().getHostAddress()+": "+ line);
				// 	if(line.equalsIgnoreCase("EXIT")){
				// 		sock.close();
				// 	}
				// }

				new ProcessClient(sock).start();
			}
		}catch(IOException e){
			System.out.println("Caught IO Exception");
		}
	}
}

class ProcesssClient extends Thread{
	Socket sock;
	ProcessClient(Socket newSock){sock = newSock;}
	public void run(){
		try{
			System.out.println(sock.getInetAddress().getHostAddress()+ "connected");
			Scanner sin =  new Scanner(sock.getInputStream());
			PrintStream sout = new PrintStream(sock.getOutputStream());
			while(sock.isConnected()){//
				//as long as connected - taking data and sending it back
				sout.print("Welcome to the Echo Server!\r\n");
				String line = sin.nextLine();
				sout.print(line+"\r\n");
				System.out.println(sock.getInetAddress().getHostAddress()+": "+ line);
				if(line.equalsIgnoreCase("EXIT")){
					sock.close();
				}
			}
		}catch(IOException e){}
	}
}

//Part 2
public class April72016HTTP1{

	static String a = "OPTION A";
	static String b = "OPTION B";

	public static void main(String[] args){
		Log l = new Log();
		try{
			ServerSocket ss = new ServerSocket(8080);
			JFrame jf = new JFrame("Vote Monitor");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(730,500);
			Monitor m = new Monitor();

			new UpdateList(m).start();
			while(true){
				Socket sock = ss.accept();
				new ProcessConnection(sock,l).start();
			}
		}catch(IOException){}
	}
}

class log{
	Arraylist<String> a;
	ArrayList<String> b;
	Log(){
		a = new Arraylist();
		b = new ArrayList();
	}
	public synchronized boolean addToList(String element, char list){
		for(String s : a)
			if(s.equals(element))
				return true;
		for(String s : b)
			if(s.equals(element))
				return true;
			if()
		if (list == 'a'){
			a.add(element);
		}
		else{
			b.add(element);
		}
	}

	public synhronized ArrayList<String> getList(char list){
		if (list == 'a'){return (ArrayList<String>)a.clone();}
		else{return (ArrayList<String>)b.clone();}
	}
}

class UpdateList extends Thread{
	Monitor m;
	Log l;
	UpdateList(Monitor newm, Log newl){m = newm; l = newl}
	public void run(){
		try{
			while(true){
				m.aList.setText("");
				String totallist = "";
				int count;
				for(String item: l.getList('a')){
					totallist += item+ "\n";
					count++;

				}
				m.List.setText(totalList);
				m.aTotal.setText(""+count);

				m.bList.setText("");
				totallist = "";
				count = 0;
				for(String item: l.getList('b')){
					totallist += item+ "\n";
					count++;

				}
				m.List.setText(totalList);
				m.bTotal.setText(""+count);
				sleep(100);
			}

		}catch(Exception e){System.out.println("Monitor gone!");}
	}
}

class ProcessConnection extends Thread{
	Log l;
	Socket sock;
	ProcessConnection(Socket newSock, Log newl){ sock = newSock; l = newl;}

	public void run(){
		try{
			Scanner sin = new Scanner(sock.getInoutStream());
			PrintStream ps = new PrintStream(sock.getOutputStream());
			String line = sin.nextLine();
			while(!line.isEmpty()){
				line = sin.nextLine();
				//Line is gonna be "GET / HTTP/1.1" - don't care about anything after that
			}
			String[] parts = firstLine.split(" ");
			String file = parts[l];
			String a = "OPTION A";
			String b = "OPTION B";
			System.out.println(sock.getInetAddress().getHostAddress() + " wants: " + file);//debug
			if(file.equals("/")){
				String.output = "<HTML><BODY><CENTER><H2>HI</H2>" + "</CENTER>Which would you choose?<br>"+ "<form action = '/a' method='GET'><input type='Submit' value='"+a+ "'><form action = '/b' method='GET'><input type='Submit' value='"+b+"'>" + "</form>";
				ps.print("HTTP/1.0 200 OK\r\n\r\n"); //this doesn't repeat
				ps.print(output+"\r\n");
			}
			else if(file.equals("/a?")){
				String output;
				if(!l.addToList(sock.getInetAddress().getHostAddress(),"a"))
					output = "<HTML><BODY>Thank you, your vote has been cast for";
				else{
					output = "<HTML><BODY>Your vote was previously counted";
				}
				sout.print("HTTP/1.0 200 OK\r\n\r\n");
				sout.print(output);
			}
			else if(file.equals("/b?")){
				l.addToList(sock.getInetAddress().getHostAddress(),"a");
				String output = "<HTML><BODY>Thank you, your vote has been cast for";
				sout.print("HTTP/1.0 200 OK\r\n\r\n");
				sout.print(output + "\r\n");
			}
			sock.close();
		}catch(IOException){}
	}
}


/*
HOMEWORK

Design a chat server

PART 1:
server component - runs in the background - no graphical interface - listens for connections - has protocol specifications
need to get name of user and lines that come in from a connection and rebroadcasted to all the other connections in the server

Client:
	2 screeens
	- text area
	- text input at the bottom - push send = sent to server
	when starts, asks person for their name
	when connected, that screen goes away, connection establishes to server and chat shows
	upon connetion, greet, accept and anything after that gets rebroadcasted
	



*/