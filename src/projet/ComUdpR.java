package projet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class ComUdpR implements Runnable {


	Entite t;


	public ComUdpR(Entite t){

		this.t=t;
	}

	public void run(){
        try{
          while(true){

			  byte[]data=new byte[100];
        Scanner sc = new Scanner(System.in);
        String st="";

              st=sc.nextLine();

        data=st.getBytes();
        InetSocketAddress ia=new  InetSocketAddress(t.myIA,t.port_udp_suivant);
        DatagramPacket paquet1=new DatagramPacket(data,data.length,ia);
        t.ds.send(paquet1);
				t.print();
      }

      } catch(Exception e){
        e.printStackTrace();
      }

      }



}
