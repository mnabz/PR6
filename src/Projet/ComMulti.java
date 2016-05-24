package Projet;

import java.net.*;
import java.io.*;

public class ComMulti implements Runnable {

	Entite t;
	public ComMulti(Entite t){
		this.t=t;
	}

	public void run() {
		try {
			MulticastSocket mso=new MulticastSocket(t.port_diff);
			mso.joinGroup(InetAddress.getByName(t.ip_diff));
		  
			byte[]data=new byte[100];
			DatagramPacket paquet=new DatagramPacket(data,data.length);
		
			while(true){
				mso.receive(paquet);
				String st=new String(paquet.getData(),0,paquet.getLength());
				System.out.println("J'ai reçu :"+st);
			}
		  	} catch(Exception e){
		  		e.printStackTrace();
		  	}
		  }

		
	}


