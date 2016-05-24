package projet;

import java.io.*;
import java.net.*;

public class EnvoiMulticast implements Runnable {

	Entite t;
	public EnvoiMulticast(Entite t){

		this.t=t;
	}

	public void run() {



		try {
			DatagramSocket dso = new DatagramSocket();
			byte[] data;

				String s = "MESSAGE +  \n";
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
