package Projet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Vector;

public class ComUdpE implements Runnable {

	Entite t;

	public ComUdpE(Entite t) {

		this.t = t;

	}

	public void run() {
		try {
			while (true) {
				byte[] data = new byte[100];
				DatagramPacket paquet = new DatagramPacket(data, data.length);
				t.ds.receive(paquet);
				String st3 = new String(paquet.getData(), 0, paquet.getLength());
				System.out.println("J'ai reçu :" + st3);

				String tab[] = st3.split(" ");
				Message mess = null;
				Vector<String> message = new Vector<String>();
				Boolean isExiste = t.recherche(mess.codeMessage, tab[0]);

				if (isExiste == true) {
					// String s=null;
					// mess =new Message(Long.valueOf(tab[1]).longValue());
					if (!t.idMess.contains(Long.valueOf(tab[1]).longValue())) {
						if (tab[0].equals("WHOS")) {// message.add(mess.WHOS());
							message.add(st3);
							Message mess1 = new Message(t.id);
							t.idMess.add(mess1.idm);
							message.add(mess1.MEMB(t.id, t.myIP, t.port_udp));

						} else if (tab[0].equals("APPL")) {
							if (tab[2].equals("DIFF####")) {
							message.add(st3);
							}
						} else if (tab[0].equals("MEMB")) {
							message.add(st3);

						} else if (tab[0].equals("GBYE")) {
							if (tab[2].equals(t.myIA)
									&& Integer.parseInt(tab[3]) == t.port_udp_suivant) {
								t.myIA = tab[4];
								t.port_udp_suivant = Integer.parseInt(tab[5]);
								Message mess1 = new Message(t.id);
								t.idMess.add(mess1.idm);
								message.add(mess1.EYBG());
							} else
								message.add(st3);

						} else if (tab[0].equals("EYBG")) {
							if (t.deco == 1)
								t.deco = 2;
							else
								message.add(st3);

						}

						for (int i = 0; i < message.size(); i++) {
							data = message.elementAt(i).getBytes();
							InetSocketAddress ia = new InetSocketAddress(
									t.myIA, t.port_udp_suivant);
							DatagramPacket paquet1 = new DatagramPacket(data,
									data.length, ia);
							t.ds.send(paquet1);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
