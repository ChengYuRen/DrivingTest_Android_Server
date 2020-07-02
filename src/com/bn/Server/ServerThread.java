package com.bn.Server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

	public static void main(String args[]){

		(new ServerThread()).start();
	}
	public ServerSocket ss;
	boolean flag=true;

	@Override
	public void run(){
		try{
			ss=new ServerSocket(9999);
			System.out.println("Congratulations!\nYour Windows server has been successfully started and " +
					"connected to the MySQL 8.0.18 database");
		}catch(Exception e){
			e.printStackTrace();
		}
		while(flag){
			try{
				Socket socket=ss.accept();
				System.out.println("Client request has arrived£º"+socket.getInetAddress());
				ServerAgentThread serverAgentThread=new ServerAgentThread(socket);
				serverAgentThread.start();
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
	}

}
