package projet;


import java.net.*;

public class MultiCastE implements Runnable {

	Entite t;
	public MultiCastE(Entite t){
		this.t=t;
	}
	
	public void run() {
		try {
			Message m = new Message("TEST", 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0);
			DatagramSocket dso = new DatagramSocket();
			byte[] data = new byte[100];
			String s =  m + " \n";
			data = s.getBytes();
			InetSocketAddress ia = new InetSocketAddress(t.ip_multi, t.port_multi);
			DatagramPacket paquet = new DatagramPacket(data, data.length,ia);
			dso.send(paquet);
			dso.close();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
