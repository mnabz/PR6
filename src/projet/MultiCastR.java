package projet;

import java.io.*;
import java.net.*;

public class MultiCastR implements Runnable {

	Entite t;

	public MultiCastR(Entite t) {
		this.t = t;
	}

	public void run() {
		try {
			MulticastSocket mso = new MulticastSocket(9999);
			mso.joinGroup(InetAddress.getByName(t.ip_multi));
			byte[] data = new byte[100];
			DatagramPacket paquet = new DatagramPacket(data, data.length);
			long start = System.currentTimeMillis();
			while (true) {
				mso.receive(paquet);
				String st = new String(paquet.getData(), 0, paquet.getLength());
				long end = System.currentTimeMillis();
				if (end - start > 30000)
				String mess = "DOWN";
				byte[] dat  new byte[100];
				String s =  s + " \n";
				dat = s.getBytes();
				InetSocketAddress ia = new InetSocketAddress(t.ip_multi, t.port_multi);
				DatagramPacket paquet2 = new DatagramPacket(dat, dat.length,ia);
				mso.send(paquet2);
				mso.disconnect();
				
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

