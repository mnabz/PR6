package projet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class ComUdpE implements Runnable {

	Entite t;

	public ComUdpE(Entite t){

		this.t=t;

	}


	      public void run(){

			try {

	              while(true){

	            byte[]data=new byte[100];
	            DatagramPacket paquet=new DatagramPacket(data,data.length);
	            t.ds.receive(paquet);
	            String st3=new String(paquet.getData(),0,paquet.getLength());
	            System.out.println("J'ai reçu :"+st3);

							int a=0,b=0,c=0,e=0,d=999999999;
							while(a<d){
								while(b<d){
									while(c<d){
										while(e<d)
									e++;
									c++;}
									b++;}
									a++;
								}


	            data=st3.getBytes();

	            InetSocketAddress ia=new  InetSocketAddress(t.myIA,t.port_udp_suivant);
	            DatagramPacket paquet1=new DatagramPacket(data,data.length,ia);
	            t.ds.send(paquet1);

	          }

	          } catch(Exception e){
	            e.printStackTrace();
	          }
	   }




}
