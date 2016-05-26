package Projet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
public class Message {



 static String[] codeMessage = { "WELC", "NEWC", "ACKC", "APPL", "WHOS",
				"MEMB", "GBYE", "EYBG", "TEST", "DOWN", "DUPL", "ACKD" };

	long idm;
	static int id;
	String idE;

	public String remplirZero(int n,String s){
		String str="";

		for(int i=0;i<n-s.length();i++)
			str+="0";

			return str+s;
	}

	public Message(String idE){
		id++;
		this.idm=Long.valueOf(idE+Integer.toString(id)).longValue();
	}

	public Message(long idm){
		this.idm=idm;
	}

	public String WHOS() throws IOException{
			return "WHOS "+Long.toString(this.idm);
	}

	public String APPL1(String mess){
		return "APPL "+Long.toString(idm)+" DIFF#### "+Integer.toString(mess.length())+" "+mess;
	}
	public String MEMB(String id,String ip,int port){
		return "MEMB "+Long.toString(this.idm)+" "+id+" "+ip+" "+Integer.toString(port);
	}
  public String GBYE(String ip,int port,String ip_succ,int port_succ){
    return "MEMB "+Long.toString(this.idm)+" "+ip+" "+Integer.toString(port)+" "+ip_succ+" "+Integer.toString(port_succ);
  }

    public String EYBG(){
      return "EYBG "+Long.toString(this.idm);

    }



}
