package projet;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.*;
import java.util.Iterator;

public class ReceiveMulticast implements Runnable {
	Entite t;

	public ReceiveMulticast(Entite t) {

		this.t = t;
	}

	public void run() {
		try {
				MulticastSocket mso=new MulticastSocket(t.port_multi);
				mso.joinGroup(InetAddress.getByName(t.ip_multi));
				byte[]data=new byte[100];
				DatagramPacket paquet=new DatagramPacket(data,data.length);
				while(true){
				mso.receive(paquet);
				String st=new String(paquet.getData(),0,paquet.getLength());
				System.out.println("J'ai reçu :"+st);
				}
		}
				catch(Exception e){
				e.printStackTrace();
		}

	}
}
